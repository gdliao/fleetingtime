package com.meeting.service.impl;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meeting.dao.RoleDao;
import com.meeting.dao.RoleFunctionDao;
import com.meeting.dao.UserDao;
import com.meeting.dao.UserRoleDao;
import com.meeting.exception.BusinessException;
import com.meeting.vo.Role;
import com.meeting.vo.RoleFunction;
import com.meeting.vo.User;
import com.meeting.vo.UserRole;
import com.meeting.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Transactional
@Service("usersService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private RoleFunctionDao roleFunctionDao;
	@Override
	public User getUserById(int id) {
		return this.userDao.selectByPrimaryKey(id);
	}

	@Override
	public List<User> queryObject(User user) {
		return userDao.selectByUserInfo(user);
	}

	@Override
	public boolean insert(User user) {
		
		try {
			userDao.insert(user);
			return true;
		} catch (Exception e) {
			throw new BusinessException("1000","新增用户异常，错误原因"+e.getMessage());
		}
		
	}
	@Override
	public PageInfo<User> queryObjectPaging(User user,Integer pageNo,Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
	    pageSize = pageSize == null?10:pageSize;
	    PageHelper.startPage(pageNo, pageSize);
	    
	    List<User> list = userDao.selectByUserInfo(user);
	    for(User u:list){
	    	List<Integer> urList = new ArrayList<Integer>();
	    	List<UserRole> trList = userRoleDao.selectByUserId(u);
	    	for(UserRole t : trList){
	    		urList.add(t.getRoleId());
	    	}
	    	u.setUserRoleList(urList);
	    }
	    //用PageInfo对结果进行包装
	    PageInfo<User> page = new PageInfo<User>(list);
	    //测试PageInfo全部属性
	    /*System.out.println(page.getPageNum());
	    System.out.println(page.getPageSize());
	    System.out.println(page.getStartRow());
	    System.out.println(page.getEndRow());
	    System.out.println(page.getTotal());
	    System.out.println(page.getPages());
	    System.out.println(page.getFirstPage());
	    System.out.println(page.getLastPage());
	    System.out.println(page.isHasPreviousPage());
	    System.out.println(page.isHasNextPage());*/
	    return page;
	}

	@Override
	public List<Role> queryRoleList(Role role) {
		
		return roleDao.selectByObject(role);
	}

	@Override
	public List<RoleFunction> queryRoleFunctionList(RoleFunction rf) {

		return roleFunctionDao.selectByRoleFunctionInfo(rf);
	}

}
