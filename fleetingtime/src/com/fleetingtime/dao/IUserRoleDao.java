package com.fleetingtime.dao;

import java.util.List;

import com.fleetingtime.vo.User;
import com.fleetingtime.vo.UserRole;

public interface IUserRoleDao {
	
    int insert(UserRole record);

    int insertSelective(UserRole record);
    
    List<UserRole> selectByUserId(User user);
}