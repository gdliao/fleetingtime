package com.meeting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meeting.dao.RoleFunctionDao;
import com.meeting.service.IRoleFunctionService;
import com.meeting.vo.RoleFunction;

@Service("roleFunctionService")
public class RoleFunctionServiceImpl implements IRoleFunctionService {
	@Resource
	private RoleFunctionDao roleFunctionDao;
	
	@Override
	public List<RoleFunction> queryObjectByRoleId(RoleFunction roleFunc) {
		
		return roleFunctionDao.selectByRoleFunctionInfo(roleFunc);
	}
}
