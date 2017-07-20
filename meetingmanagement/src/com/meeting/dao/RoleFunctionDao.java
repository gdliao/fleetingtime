package com.meeting.dao;

import java.util.List;

import com.meeting.vo.RoleFunction;

public interface RoleFunctionDao {
    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);
    
    List<RoleFunction> selectByRoleFunctionInfo(RoleFunction roleFunction);
    
    int deleteRoleFunction(RoleFunction roleFunction);
}