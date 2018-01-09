package com.meeting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meeting.exception.BusinessException;
import com.meeting.service.IBookingService;
import com.meeting.service.IRoomService;
import com.meeting.vo.BookingInfo;
import com.meeting.vo.Equipment;
import com.meeting.vo.Room;

@Controller
@RequestMapping("/booking")
public class BookingController extends BaseController {
	@Resource
	private IRoomService roomService;
	@Resource
	private IBookingService bookingService;
	
	@RequestMapping("/queryRoomBookingInfo")
	public @ResponseBody Map<String,Object> queryRoomBookingInfo(HttpServletRequest request,
			HttpServletResponse response){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			BookingInfo bookingInfo = new BookingInfo();
			bookingInfo.setRoomId(roomId);
			List<BookingInfo> bookingInfoList = bookingService.queryBookingInfo(bookingInfo);
			
			if(bookingInfoList.size()>0){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				map.put("data", bookingInfoList);
			}else{
				map.put("result", SUCCESS);
				map.put("errorCode", "1000");
				map.put("data", "所选房间无预约。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/queryRoomInfo")
	public @ResponseBody Map<String,Object> queryRoomInfo(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Room room = new Room();
			List<Room> roomInfoList = roomService.queryObject(room);
			
			if(roomInfoList.size()!=0){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				map.put("data", roomInfoList);
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "1001");
				map.put("errorMsg", "未查询到会议室信息。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/queryEquipment")
	public @ResponseBody Map<String,Object> queryEquipment(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			Equipment equipment = new Equipment();
			List<Equipment> equipmentList = roomService.queryEquipmentByObject(equipment);
			
			if(equipmentList.size()!=0){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				map.put("data", equipmentList);
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "1001");
				map.put("errorMsg", "未查询到会议室信息。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/commitBookingInfo")
	public @ResponseBody Map<String,Object> commitBookingInfo(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			JSONObject reqJson =  JSON.parseObject(request.getParameter("param"));
			
			reqJson.put("userId", session.getAttribute("userId"));
			
			if(bookingService.bookingInfoCommit(reqJson)){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
			}
		}catch (BusinessException e) {
			
			map.put("result", ERROR);
			map.put("errorCode", e.getRetCode());
			map.put("errorMsg", e.getRetMsg());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", e.getMessage());
		}
		
		return map;
	}
	@RequestMapping("/queryMyBookingInfo")
	public @ResponseBody Map<String,Object> queryMyBookingInfo(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			BookingInfo bookingInfo = new BookingInfo();
			bookingInfo.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
			List<BookingInfo> bookingInfoList = bookingService.queryAllBookingInfo(bookingInfo);
			
			if(bookingInfoList.size()>0){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				map.put("data", bookingInfoList);
			}else{
				map.put("result", SUCCESS);
				map.put("errorCode", "1000");
				map.put("data", "无预定信息。");
			}
		}catch (BusinessException e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", e.getRetCode());
			map.put("errorMsg", e.getRetMsg());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/editMyBooking")
	public @ResponseBody Map<String,Object> editMyBooking(HttpServletRequest request,
			HttpServletResponse response){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			BookingInfo bookingInfo = new BookingInfo();
			
			JSONObject reqJson =  JSON.parseObject(request.getParameter("param"));

			if(bookingService.editMyBookingInfo(reqJson)){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "1000");
				map.put("data", "无任何预约信息。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/deleteRecord")
	public @ResponseBody Map<String,Object> deleteRecord(HttpServletRequest request,
			HttpServletResponse response){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			BookingInfo bookingInfo = new BookingInfo();
			
			int bookingId =  Integer.parseInt(request.getParameter("bookingId"));
			bookingInfo.setBookingId(bookingId);
			
			if(bookingService.deleteRecord(bookingInfo)){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
			}
		}catch (BusinessException e){
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", e.getRetCode());
			map.put("errorMsg", e.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
}
