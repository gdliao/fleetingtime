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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.meeting.exception.BusinessException;
import com.meeting.service.IRoleFunctionService;
import com.meeting.service.IUserRoleService;
import com.meeting.service.IUserService;
import com.meeting.vo.Role;
import com.meeting.vo.RoleFunction;
import com.meeting.vo.User;


@Controller
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {
	
	@Resource
	private IUserService userService;
	@Resource
	private IUserRoleService userRoleService;
	@Resource
	private IRoleFunctionService roleFunctionService;
	
	@RequestMapping("/queryRoleList")
	public @ResponseBody Map<String, Object> queryRoleList(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			Role role = new Role();
			
			if(null!=request.getParameter("roleId")&&!"".equals(request.getParameter("roleId"))){
				role.setRoleId(Integer.parseInt(request.getParameter("roleId")));
			}
			
			List<Role> data = userService.queryRoleList(role);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			map.put("data", data);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/getRoleTreeList")
	public @ResponseBody Map<String, Object> getRoleTreeList(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			JSONArray jsonArray = userRoleService.getRoleTreeJson();
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			map.put("data", jsonArray);
			
		} catch (BusinessException e){
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
	
	@RequestMapping("/queryRoleListPaging")
	public @ResponseBody
	Map<String, Object> queryRoleListPaging(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			User user = new User();
			int pageNo = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			
			Role role = new Role();
			PageInfo<Role> data = userRoleService.queryRoleListPaging(role, pageNo,
					pageSize);


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
	
	@RequestMapping("/queryRoleFunctionList")
	public @ResponseBody Map<String, Object> queryRoleFunctionList(HttpServletRequest request,
			HttpServletResponse response){
 		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			Role role = new Role();
			RoleFunction rf = new RoleFunction();
			if(null!=request.getParameter("roleId")&&!"".equals(request.getParameter("roleId"))){
				rf.setRoleId(Integer.parseInt(request.getParameter("roleId")));
			}
			
			List<RoleFunction> data = userService.queryRoleFunctionList(rf);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			map.put("data", data);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}

	@RequestMapping("/addRole")
	public @ResponseBody Map<String, Object> addRole(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {

			JSONArray roleFuncIdList = JSON.parseArray(request.getParameter("roleFuncIdList"));
			String roleName = request.getParameter("roleName");
			
			JSONObject roleInfo = new JSONObject();
			
			roleInfo.put("roleName", roleName);
			roleInfo.put("roleFuncIdList", roleFuncIdList);
			
			userRoleService.addRole(roleInfo);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			
		} catch (BusinessException e){
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
	
	@RequestMapping("/editRole")
	public @ResponseBody Map<String, Object> editRole(HttpServletRequest request,
			HttpServletResponse response){
 		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			JSONArray roleFuncIdList = JSON.parseArray(request.getParameter("roleFuncIdList"));
			
			JSONObject roleInfo = new JSONObject();
			
			roleInfo.put("roleId", roleId);
			roleInfo.put("roleFuncIdList", roleFuncIdList);

			userRoleService.editRole(roleInfo);
			
			map.put("result", SUCCESS);
			map.put("errorCode", "0000");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "系统错误，请联系管理员。         错误原因：" + e.getMessage());
		}
		
		return map;
	}
}
