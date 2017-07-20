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

@Controller
@RequestMapping("/myBooking")
public class MyBookingController extends BaseController {
	@Resource
	private IRoomService roomService;
	@Resource
	private IBookingService bookingService;
	
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
