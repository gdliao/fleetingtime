package com.fleetingtime.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fleetingtime.constant.DateConst;
import com.fleetingtime.service.IUserRoleService;
import com.fleetingtime.service.IUserService;
import com.fleetingtime.service.IVoteService;
import com.fleetingtime.utils.DateUtil;
import com.fleetingtime.utils.MD5;
import com.fleetingtime.vo.User;
import com.fleetingtime.vo.UserRole;
import com.fleetingtime.vo.Vote;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private IUserService userService;
	@Resource
	private IUserRoleService userRoleService;
	@Resource
	private IVoteService voteService;

	@RequestMapping("/login")
	public @ResponseBody
	Map<String, Object> login() {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			String loginNo = request.getParameter("loginNo");
			String password = request.getParameter("password");
			String rand = request.getParameter("rand");
			
			User user = new User();
			List<User> userList = new ArrayList<User>();
		
			//判断验证码
			if(rand.equals(session.getAttribute("rand"))){
				//判断登陆号码是手机号还是邮箱账号
				if(loginNo.indexOf("@")>=0){//登录账号为邮箱账号
					user.setEmail(loginNo);
				}else{
					user.setPhoneNum(loginNo);
				}
				userList = this.userService.queryObject(user);
				if(userList.size()==1){
					user = userList.get(0);
					if (null != user && new MD5().getMD5ofStr(password).equals(user.getPassword())) {
						// 在Session里保存信息
						session.setAttribute("userName", user.getUserName());
						session.setAttribute("phoneNum", user.getPhoneNum());
						session.setAttribute("email", user.getEmail());
						session.setAttribute("userId", user.getUserId());
						session.setAttribute("memberStatus", user.getMemberStatus());
						
						String likeList = voteService.queryList(getUserId());
						
						JSONObject json = new JSONObject();
						json.put("userId", user.getUserId());
						json.put("likeList", likeList);
						
						session.setAttribute("likeList", likeList);
						
						map.put("result", SUCCESS);
						map.put("errorCode", "0000");
						map.put("data", json);

					} else {
						// 清除Session
						session.invalidate();

						map.put("result", ERROR);
						map.put("errorCode", "1000");
						map.put("errorMsg", "账号或密码错误。");

					}
				}else if(userList.size()==0){//用户信息不存在
					
					// 清除Session
					session.invalidate();

					map.put("result", ERROR);
					map.put("errorCode", "1001");
					map.put("errorMsg", "用户信息不存在，请先注册。");
				}else{//登录判断报错 数据库中有相同登录信息用户
					// 清除Session
					session.invalidate();

					map.put("result", ERROR);
					map.put("errorCode", "1002");
					map.put("errorMsg", "系统错误，请联系管理员。");
				}
			}else{
				// 清除Session
				session.invalidate();

				map.put("result", ERROR);
				map.put("errorCode", "1000");
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
	public @ResponseBody Map<String, Object> register(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		String rand = request.getParameter("rand");
		
		try {
				//判断验证码
				if(rand.equals(session.getAttribute("rand"))){
					
					String userName = request.getParameter("userName");
					String phoneNum = request.getParameter("phoneNum");
					String email = request.getParameter("email");
					String password = new MD5().getMD5ofStr(request.getParameter("password"));
					User user = new User();
					
					user.setUserName(userName);
					user.setPhoneNum(phoneNum);
					user.setEmail(email);
					user.setPassword(password);
					
					/*暂时默认*/
					user.setMemberStatus("1");
					user.setEndTime(DateUtil.stringtoDate("2099-12-31", DateConst.DATE_MODEL_5));
				
					if(userService.insert(user)){
						
						/*注册结束后直接登录*/
						/*给session赋值*/
						List<User> userList = this.userService.queryObject(user);
						if(userList.size()==1){
							user = userList.get(0);
							// 在Session里保存信息
							session.setAttribute("userName", user.getUserName());
							session.setAttribute("phoneNum", user.getPhoneNum());
							session.setAttribute("email", user.getEmail());
							session.setAttribute("userId", user.getUserId());
							session.setAttribute("memberStatus", user.getMemberStatus());
							
							String likeList = voteService.queryList(getUserId());
							
							JSONObject json = new JSONObject();
							json.put("userId", user.getUserId());
							json.put("likeList", likeList);
							
							session.setAttribute("likeList", likeList);
							
							map.put("result", SUCCESS);
							map.put("errorCode", "0000");
							map.put("data", json);
					
							map.put("result", SUCCESS);
							map.put("errorCode", "0000");
							
						}else{
							map.put("result", ERROR);
							map.put("errorCode", "3000");
							map.put("errorMsg", "注册后登录失败！");
						}
					}else{
						map.put("result", ERROR);
						map.put("errorCode", "2000");
						map.put("errorMsg", "注册失败！");
				}
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "1000");
				map.put("errorMsg", "验证码输入错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "注册失败！         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("logout")
	public @ResponseBody Map<String, Object> logout(HttpSession session){
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
	
	/*@RequestMapping("getuserspaging")
	public @ResponseBody Map<String, Object> getuserspaging(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			User user = new User();
			int pageNo = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			PageInfo<User> data = userService.queryObjectPaging(user, pageNo, pageSize);
			
			//map.put("data", data);
			map.put("rows", data.getList());
			map.put("total", data.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}*/
	
	
}
