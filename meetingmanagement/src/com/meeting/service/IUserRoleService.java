package com.meeting.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.meeting.vo.Role;
import com.meeting.vo.User;
import com.meeting.vo.UserRole;

public interface IUserRoleService {
	
	public List<UserRole> queryObjectByUserId(User user);
	
	/**
	 * 根据用户查询用户对应功能点 
	 * @param user
	 * @return 用户所对应角色包含所有功能点
	 * @author liaogd
	 * */
	public List<JSONObject> queryUserRoleFunc(User user);
	
	/**
	 * 查询所有用户权限 并拼装为tree json返回
	 * @return json
	 * */
	public JSONArray getRoleTreeJson();
	
	/**
	 * 分页查询权限列表
	 * */
	public PageInfo<Role> queryRoleListPaging(Role role,Integer pageNo,
			Integer	pageSize);
	
	/**
	 * 新增角色方法
	 * */
	public boolean addRole(JSONObject roleInfo);
	
	/**修改角色功能点方法*/
	public boolean editRole(JSONObject roleInfo);
}
