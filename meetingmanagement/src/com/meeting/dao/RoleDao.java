package com.meeting.dao;

import java.util.List;

import com.meeting.vo.Role;

public interface RoleDao {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByObject(Role role);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}