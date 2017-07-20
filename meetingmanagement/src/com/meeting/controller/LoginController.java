package com.meeting.controller;

import java.util.ArrayList;
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

import com.meeting.service.IUserRoleService;
import com.meeting.service.IUserService;
import com.meeting.utils.MD5;
import com.meeting.vo.User;
import com.meeting.vo.UserRole;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Resource
	private IUserService userService;
	@Resource
	private IUserRoleService userRoleService;
	
	@RequestMapping("/login")
	public @ResponseBody
	Map<String, Object> login(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			String loginNo = request.getParameter("loginNo");
			String password = request.getParameter("password");
			String rand = request.getParameter("rand");
			
			User user = new User();
			List<User> userList = new ArrayList<User>();
			
			//判断验证码是否正确
			if(rand.equals(session.getAttribute("rand"))){
				//判断登陆号码是手机号还是邮箱账号
				if(loginNo.indexOf("@")>=0){//登录账号为邮箱账号
					user.setEmail(loginNo);
				}else{
					user.setPhoneNumber(loginNo);
				}
				userList = this.userService.queryObject(user);
				
				if(userList.size()==1){
					user = userList.get(0);
					if (null != user && new MD5().getMD5ofStr(password).equals(user.getPassword())) {
						// 在Session里保存信息
						session.setAttribute("userName", user.getUserName());
						session.setAttribute("phoneNum", user.getPhoneNumber());
						session.setAttribute("email", user.getEmail());
						session.setAttribute("userId", user.getUserId());
						
						List<UserRole> tList = userRoleService.queryObjectByUserId(user);
						List<String> userRoleList = new ArrayList<String>();
						if(tList.size()>0){
							for(UserRole ur : tList){
								userRoleList.add(ur.getRoleId().toString());
							}
							session.setAttribute("roles", userRoleList);
						}
						
						map.put("result", SUCCESS);
						map.put("errorCode", "0000");

					} else {
						// 清除Session
						session.invalidate();

						map.put("result", ERROR);
						map.put("errorCode", "1000");
						map.put("errorMsg", "密码错误。");

					}
				}else if(userList.size()==0){
					// 清除Session
					session.invalidate();

					map.put("result", ERROR);
					map.put("errorCode", "1001");
					map.put("errorMsg", "用户尚未注册或账号输入错误。");
				}else{//登录判断报错 数据库中有相同登录信息用户
					
					// 清除Session
					session.invalidate();

					map.put("result", ERROR);
					map.put("errorCode", "1002");
					map.put("errorMsg", "系统异常，请联系管理员。");
				}
			}else{
				// 清除Session
				session.invalidate();

				map.put("result", ERROR);
				map.put("errorCode", "1003");
				map.put("errorMsg", "验证码输入错误。");
			}
			
		} catch (Exception e) {

			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());

		}

		return map;
	}
	@RequestMapping("/register")
	public @ResponseBody Map<String, Object> register(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			String userName = request.getParameter("userName");
			String phoneNum = request.getParameter("phoneNum");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			/*User user = new User();
			
			user.setUserName(userName);
			user.setPhoneNum(phoneNum);
			user.setEmail(email);
			user.setPassword(password);
			
			if(userService.insert(user)){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "1000");
				map.put("errorMsg", "注册失败！");
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "注册失败！         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("getUserFunc")
	public @ResponseBody Map<String, Object> getUserFunc(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = new User();
			String userId = session.getAttribute("userId").toString();
			user.setUserId(Integer.parseInt(userId));
			
			List<JSONObject> userRoleList = userRoleService.queryUserRoleFunc(user);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			map.put("data", userRoleList);
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统异常！         错误原因：" + e.getMessage());
		}
		
		
		return map;
	}
	
	@RequestMapping("exit")
	public @ResponseBody Map<String, Object> exit(HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 清除Session
			session.invalidate();
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统异常！         错误原因：" + e.getMessage());
		}
		return map;
		
	}
	
}
