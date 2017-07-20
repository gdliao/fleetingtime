package com.meeting.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meeting.dao.BookingEquipmentDao;
import com.meeting.dao.BookingInfoDao;
import com.meeting.dao.SequenceDao;
import com.meeting.exception.BusinessException;
import com.meeting.service.IBookingService;
import com.meeting.vo.BookingEquipment;
import com.meeting.vo.BookingInfo;
import com.meeting.vo.Sequence;

@Transactional
@Service("bookingService")
public class BookingServiceImpl implements IBookingService {
	@Resource
	private BookingEquipmentDao bookingEquipmentDao;
	@Resource
	private BookingInfoDao bookingInfoDao;
	@Resource
	private SequenceDao sequenceDao;
	
	@Transactional
	@Override
	public boolean bookingInfoCommit(JSONObject jsonObject) throws Exception{
		
		
			Date startTime = jsonObject.getDate("startTime");
			Date endTime = jsonObject.getDate("endTime");
			Integer roomId = jsonObject.getInteger("roomId");
			String title = jsonObject.getString("title");
			Integer userId = jsonObject.getInteger("userId");
			JSONArray equipList = jsonObject.getJSONArray("equipList");
			
			BookingInfo bookingInfo = new BookingInfo();
			bookingInfo.setUserId(userId);
			bookingInfo.setRoomId(roomId);
			bookingInfo.setMeetingTitle(title);
			bookingInfo.setStartTime(startTime);
			bookingInfo.setEndTime(endTime);
			
			List<BookingInfo> tempList = new ArrayList<BookingInfo>();
			tempList = bookingInfoDao.selectOverStartTime(bookingInfo);
			if(tempList.size()>0){
				throw new BusinessException("1001","所选时间段有正在进行的会议！");
			}
			
			bookingInfoDao.insert(bookingInfo);
			
			
			Sequence sequence = new Sequence();
			sequence.setName("seq_booking_id");
			Integer bookingId = sequenceDao.selectCurrval(sequence).getCurrentValue();
			
			for(int i=0;i<equipList.size();i++){
				
				BookingEquipment bookingEquipment = new BookingEquipment();
				
				Integer equipId = equipList.getInteger(i);
				
				bookingEquipment.setBookingId(bookingId);
				bookingEquipment.setEquipId(equipId);
				
				bookingEquipmentDao.insert(bookingEquipment);
			}
			
			return true;
		
	}

	@Override
	public List<BookingInfo> queryBookingInfo(BookingInfo bookingInfo) {
		// TODO Auto-generated method stub
		return bookingInfoDao.selectOverCurTime(bookingInfo);
	}

	@Override
	public List<BookingInfo> queryAllBookingInfo(BookingInfo bookingInfo) {
		// TODO Auto-generated method stub
		List<BookingInfo> resList = new ArrayList<BookingInfo>();
		List<BookingInfo> tempList = bookingInfoDao.selectByBookingInfo(bookingInfo);
		
		for(int i=0;i<tempList.size();i++){
			int bookingId = tempList.get(i).getBookingId();
			BookingEquipment be = new BookingEquipment();
			be.setBookingId(bookingId);
			List<BookingEquipment> tempBeList = bookingEquipmentDao.selectByBookingEquipmentInfo(be);
			List<Integer> tempEquipList = new ArrayList<Integer>();
			for(int j=0;j<tempBeList.size();j++){
				tempEquipList.add(tempBeList.get(j).getEquipId());
			}
			tempList.get(i).setEquipList(tempEquipList);
			resList.add(tempList.get(i));
		}
		return resList;
	}

	@Transactional
	@Override
	public boolean editMyBookingInfo(JSONObject jsonObject) throws Exception{
		try{
			BookingInfo bookingInfo = new BookingInfo();
			
			bookingInfo.setBookingId(jsonObject.getInteger("bookingId"));
			bookingInfo.setMeetingTitle(jsonObject.getString("meetingTitle"));
			bookingInfo.setStartTime(jsonObject.getDate("startTime"));
			bookingInfo.setEndTime(jsonObject.getDate("endTime"));
			bookingInfo.setRoomId(jsonObject.getInteger("roomId"));
			
			//更新预约信息表
			bookingInfoDao.updateBookingInfo(bookingInfo);
		
			//更新预约设备表  先删后插
			BookingEquipment bookingEquipment = new BookingEquipment();
			int bookingId = jsonObject.getInteger("bookingId");
			bookingEquipment.setBookingId(bookingId);
			List<BookingEquipment> tempList = bookingEquipmentDao.selectByBookingEquipmentInfo(bookingEquipment);
			if(tempList.size()>0){
				bookingEquipmentDao.deleteByBookingEquipmentInfo(bookingEquipment);
			}
			for(int i=0;i<jsonObject.getJSONArray("equipList").size();i++){
				BookingEquipment be = new BookingEquipment();
				be.setBookingId(bookingId);
				be.setEquipId(Integer.parseInt(jsonObject.getJSONArray("equipList").getString(i)));
				bookingEquipmentDao.insert(be);
			}
			
			return true;
		}catch(Exception e){
			throw new BusinessException("1002","修改预约信息服务异常："+e.getMessage());
		}
		
		
	}

	@Transactional
	@Override
	public boolean deleteRecord(BookingInfo bookingInfo) throws Exception {
		
		try {
			//删除预约信息记录
			bookingInfoDao.deleteRecord(bookingInfo);
			
			//删除预约设备记录
			int bookingId = bookingInfo.getBookingId();
			BookingEquipment bookingEquipment = new BookingEquipment();
			bookingEquipment.setBookingId(bookingId);
			List<BookingEquipment> tempList = bookingEquipmentDao.selectByBookingEquipmentInfo(bookingEquipment);
			if(tempList.size()>0){
				bookingEquipmentDao.deleteByBookingEquipmentInfo(bookingEquipment);
			}
			
			return true;
		} catch (Exception e) {
			throw new BusinessException("1003", "删除数据异常："+e.getMessage());
		}
		
		
		
	}
	
	
}
