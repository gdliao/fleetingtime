package com.fleetingtime.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fleetingtime.dao.IUserRoleDao;
import com.fleetingtime.service.IUserRoleService;
import com.fleetingtime.vo.User;
import com.fleetingtime.vo.UserRole;
@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
	@Resource
	private IUserRoleDao userRoleDao;
	
	@Override
	public List<UserRole> queryObjectByUserId(User user) {
		return userRoleDao.selectByUserId(user);
	}
	
}
