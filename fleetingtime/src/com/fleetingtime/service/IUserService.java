package com.fleetingtime.service;

import java.util.List;

import com.fleetingtime.vo.User;
import com.github.pagehelper.PageInfo;

public interface IUserService {

	public User getUserById(int id);
	
	public List<User> queryObject(User user);
	
	public boolean insert(User user);

	List<User> getAllUsers();
	
	//public PageInfo<User> queryObjectPaging(User user,Integer pageNo,Integer pageSize);
	
}
