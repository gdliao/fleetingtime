package com.meeting.dao;

import java.util.List;

import com.meeting.vo.User;
import com.meeting.vo.UserRole;

public interface UserRoleDao {
    int insert(UserRole record);

    int insertSelective(UserRole record);
    
    List<UserRole> selectByUserId(User user);
}