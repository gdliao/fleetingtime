package com.meeting.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.dao.FunctionDao;
import com.meeting.dao.RoleDao;
import com.meeting.dao.RoleFunctionDao;
import com.meeting.dao.UserRoleDao;
import com.meeting.exception.BusinessException;
import com.meeting.service.IUserRoleService;
import com.meeting.vo.Function;
import com.meeting.vo.Role;
import com.meeting.vo.RoleFunction;
import com.meeting.vo.User;
import com.meeting.vo.UserRole;

@Transactional
@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private RoleFunctionDao roleFunctionDao;
	@Resource
	private FunctionDao functionDao;
	@Resource
	private RoleDao roleDao;
	@Override
	public List<UserRole> queryObjectByUserId(User user) {
		return userRoleDao.selectByUserId(user);
	}

	@Override
	public List<JSONObject> queryUserRoleFunc(User user) {
		
		List<JSONObject> roles = new ArrayList<JSONObject>();
		//查询用户包含几个角色
		List<UserRole> userRoleList = queryObjectByUserId(user);
		//组装辅助对象
		List<JSONObject> json = new ArrayList<JSONObject>();
		//存放用户所有功能点信息
		List<JSONObject> roleFuncsList = new ArrayList<JSONObject>();
		//遍历用户所有角色
		for(int i=0;i<userRoleList.size();i++){
			
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setRoleId(userRoleList.get(i).getRoleId());
			//查询角色对应的功能点编码
			List<RoleFunction> roleFuncList = roleFunctionDao.selectByRoleFunctionInfo(roleFunction);
			
			for(int j=0;j<roleFuncList.size();j++){
				JSONObject roleFuncs = new JSONObject();
				Function func = new Function();
				func.setFuncId(roleFuncList.get(j).getFuncId());
				//查询功能编码对应的功能点信息
				Function tempFunc = functionDao.selectByFunctionInfo(func);
				roleFuncs.put("func", tempFunc);
				roleFuncsList.add(roleFuncs);
			}
		}
		
		return roleFuncsList;
	}

	@Override
	public JSONArray getRoleTreeJson() {
		JSONArray resJsonArray = new JSONArray();
		try {
			Function func = new Function();
			List<Function> funcList = functionDao.selectFunctionList(func);
			
			for(Function tempF : funcList){
				JSONArray childJsonArray = new JSONArray();
				if(tempF.getParentId()==0){
					JSONObject tempObj = new JSONObject();
					tempObj.put("id", tempF.getFuncId());
					tempObj.put("text", tempF.getFuncName());
					tempObj.put("iconCls", "icon-blank");
					for(Function tempChildF : funcList){
						JSONObject tempChildObj = new JSONObject();
						if(tempChildF.getParentId()==tempF.getFuncId()){
							tempChildObj.put("id", tempChildF.getFuncId());
							tempChildObj.put("text", tempChildF.getFuncName());
							tempChildObj.put("iconCls", "icon-blank");
							childJsonArray.add(tempChildObj);
						}
					}
					tempObj.put("children", childJsonArray);
					resJsonArray.add(tempObj);
				}
			}
			
			return resJsonArray;
		} catch (Exception e) {
			throw new BusinessException("1000",e.getMessage());
		}
	}

	@Override
	public PageInfo<Role> queryRoleListPaging(Role role, Integer pageNo,
			Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
	    pageSize = pageSize == null?10:pageSize;
	    PageHelper.startPage(pageNo, pageSize);
	    
	    List<Role> list = roleDao.selectByObject(role);
	    //用PageInfo对结果进行包装
	    PageInfo<Role> page = new PageInfo<Role>(list);

	    return page;
	}

	@Transactional
	@Override
	public boolean addRole(JSONObject roleInfo) {
		
		try {
			
			String roleName = roleInfo.getString("roleName");
			JSONArray tList = roleInfo.getJSONArray("roleFuncIdList");
			
			Role role = new Role();
			role.setRoleName(roleName);
			role.setRoleLevel(1);
			roleDao.insert(role);
			
			int roleId = roleDao.selectByObject(role).get(0).getRoleId();
			for(int i=0;i<tList.size();i++){
				Function func = new Function();
				func.setFuncId(Integer.parseInt(tList.get(i).toString()));
				func = functionDao.selectByFunctionInfo(func);
				if(!tList.contains(func.getParentId())&&func.getParentId()!=0){
					tList.add(func.getParentId());
				}
			}
			
			for(int i=0;i<tList.size();i++){
				RoleFunction roleFunc = new RoleFunction();
				roleFunc.setRoleId(roleId);
				roleFunc.setFuncId(Integer.parseInt(tList.get(i).toString()));
				
				roleFunctionDao.insert(roleFunc);
			}
			
		} catch (Exception e) {
			throw new BusinessException("1000",e.getMessage());
		}
		return true;
	}
	
	@Transactional
	@Override
	public boolean editRole(JSONObject roleInfo) {
		
		try {
			
			int roleId = roleInfo.getInteger("roleId");
			JSONArray tList = roleInfo.getJSONArray("roleFuncIdList");
			
			for(int i=0;i<tList.size();i++){
				Function func = new Function();
				func.setFuncId(Integer.parseInt(tList.get(i).toString()));
				func = functionDao.selectByFunctionInfo(func);
				if(!tList.contains(func.getParentId())&&func.getParentId()!=0){
					tList.add(func.getParentId());
				}
			}
			
			RoleFunction rf = new RoleFunction();
			rf.setRoleId(roleId);
			roleFunctionDao.deleteRoleFunction(rf);
			
			for(int i=0;i<tList.size();i++){
				RoleFunction roleFunc = new RoleFunction();
				roleFunc.setRoleId(roleId);
				roleFunc.setFuncId(Integer.parseInt(tList.get(i).toString()));
				
				roleFunctionDao.insert(roleFunc);
			}
			
		} catch (Exception e) {
			throw new BusinessException("1000",e.getMessage());
		}
		return true;
	}
}
