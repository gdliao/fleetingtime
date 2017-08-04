package com.fleetingtime.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fleetingtime.dao.IUserRoleDao;
import com.fleetingtime.exception.BusinessException;
import com.fleetingtime.service.IUserRoleService;
import com.fleetingtime.vo.User;
import com.fleetingtime.vo.UserRole;
@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
	@Resource
	private IUserRoleDao userRoleDao;
	
	@Override
	public List<UserRole> queryObjectByUserId(User user) {
		try {
			return userRoleDao.selectByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("1000", e.getMessage());
		}
		
	}
	
}
