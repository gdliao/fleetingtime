package com.fleetingtime.service;

import java.util.List;

import com.fleetingtime.vo.User;
import com.fleetingtime.vo.UserRole;

public interface IUserRoleService {
	
	public List<UserRole> queryObjectByUserId(User user);
}
