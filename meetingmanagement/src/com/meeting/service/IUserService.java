package com.meeting.service;

import java.util.List;

import com.meeting.vo.Role;
import com.meeting.vo.RoleFunction;
import com.meeting.vo.User;
import com.github.pagehelper.PageInfo;

public interface IUserService {

	public User getUserById(int id);
	
	public List<User> queryObject(User user);
	
	public boolean insert(User user);
	
	public PageInfo<User> queryObjectPaging(User user,Integer pageNo,Integer pageSize);
	
	public List<Role> queryRoleList(Role role);
	
	public List<RoleFunction> queryRoleFunctionList(RoleFunction rf);
}
