package com.meeting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.meeting.exception.BusinessException;
import com.meeting.service.IMsgService;
import com.meeting.service.IUserService;
import com.meeting.vo.Msg;
import com.meeting.vo.User;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IMsgService msgService;
	
	@RequestMapping("/queryUserList")
	public @ResponseBody Map<String, Object> queryUserList(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			User user = new User();
			int pageNo = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			String userName = request.getParameter("userName");
			user.setUserName(userName);
			
			PageInfo<User> data = userService.queryObjectPaging(user, pageNo, pageSize);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			map.put("rows", data.getList());
			map.put("total", data.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/addUser")
	public @ResponseBody Map<String, Object> addUser(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			JSONObject reqJson =  JSON.parseObject(request.getParameter("param"));
			
			User user = new User();
			user.setUserName(reqJson.getString("userName"));
			user.setPhoneNumber(reqJson.getString("phoneNumber"));
			user.setEmail(reqJson.getString("email"));
			user.setPassword("F379EAF3C831B04DE153469D1BEC345E");
			
			if(userService.insert(user)){
				
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				
			}else{
				
				map.put("result", ERROR);
				map.put("errorCode", "1001");
				map.put("errorMsg",  "新增用户方法异常！");
			
			}
		}catch (BusinessException e){
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", e.getRetCode());
			map.put("errorMsg",  e.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/queryUserMsg")
	public @ResponseBody Map<String, Object> queryUserMsg(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Msg msg = new Msg();
			msg.setMsgTargetUserId(getUserId());
			msg.setMsgStatus(1);
			int count = msgService.count(msg);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			map.put("count", count);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/queryUserMsgList")
	public @ResponseBody Map<String, Object> queryUserMsgList(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			User user = new User();
			user.setUserId(getUserId());
			List<Msg> list = msgService.queryMsgListByUser(user);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			map.put("data", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/replyMsg")
	public @ResponseBody Map<String, Object> replyMsg(Msg msg){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			msg.setUserId(getUserId());
			
			if(msgService.replyMsg(msg)){
				
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				
			}
		} catch (BusinessException be){
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + be.getRetMsg());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
}
