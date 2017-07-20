package com.meeting.service;

import java.util.List;

import com.meeting.vo.RoleFunction;

public interface IRoleFunctionService {
	
	public List<RoleFunction> queryObjectByRoleId(RoleFunction roleFunc);
}
