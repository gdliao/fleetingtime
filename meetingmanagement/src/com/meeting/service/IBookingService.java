package com.meeting.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.meeting.vo.BookingInfo;

public interface IBookingService {
	/**
	 * 预定会议室提交方法
	 * @throws Exception 
	 * 
	 * */
	public boolean bookingInfoCommit(JSONObject jsonObject) throws Exception;
	
	/**
	 * 查询预约和正在使用的会议室信息
	 *  
	 * */
	public List<BookingInfo> queryBookingInfo(BookingInfo bookingInfo);
	
	/**
	 * 查询所有会议室信息
	 * */
	public List<BookingInfo> queryAllBookingInfo(BookingInfo bookingInfo);
	
	/**
	 * 修改预定内容
	 * */
	public boolean editMyBookingInfo(JSONObject jsonObject) throws Exception;
	
	/**
	 * 删除记录
	 * */
	public boolean deleteRecord(BookingInfo bookingInfo) throws Exception;
}
