package com.fleetingtime.service.impl;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleetingtime.dao.IUserDao;
import com.fleetingtime.vo.User;
import com.fleetingtime.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Transactional
@Service("usersService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int id) {
		return this.userDao.selectByPrimaryKey(id);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return this.userDao.selectAllUser();
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
			e.printStackTrace();
			return false;
		}
		
	}
	/*@Override
	public PageInfo<User> queryObjectPaging(User user,Integer pageNo,Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
	    pageSize = pageSize == null?10:pageSize;
	    PageHelper.startPage(pageNo, pageSize);
	    List<User> list = userDao.selectByUserInfo(user);
	    //用PageInfo对结果进行包装
	    PageInfo<User> page = new PageInfo<User>(list);
	    //测试PageInfo全部属性
	    System.out.println(page.getPageNum());
	    System.out.println(page.getPageSize());
	    System.out.println(page.getStartRow());
	    System.out.println(page.getEndRow());
	    System.out.println(page.getTotal());
	    System.out.println(page.getPages());
	    System.out.println(page.getFirstPage());
	    System.out.println(page.getLastPage());
	    System.out.println(page.isHasPreviousPage());
	    System.out.println(page.isHasNextPage());
	    return page;
	}*/

}
