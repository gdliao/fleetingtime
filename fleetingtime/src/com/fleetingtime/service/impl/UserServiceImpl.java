package com.fleetingtime.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleetingtime.dao.IUserDao;
import com.fleetingtime.exception.BusinessException;
import com.fleetingtime.utils.MD5;
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
		try {
			return this.userDao.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("1000", e.getMessage());
		}
	}

	@Override
	public List<User> getAllUsers() {
		try {
			return this.userDao.selectAllUser();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("1000", e.getMessage());
		}
	}

	@Override
	public List<User> queryObject(User user) {
		try {
			return userDao.selectByUserInfo(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("1000", e.getMessage());
		}
	}

	@Override
	public boolean insert(User user) {

		try {
			userDao.insert(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			throw new BusinessException("1000", e.getMessage());
		}

	}

	@Override
	public boolean update(User user) {
		try {
			user.setPassword(new MD5().getMD5ofStr(user.getPassword()));
			userDao.updateByPrimaryKeySelective(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("1000", e.getMessage());
		}

	}

	/*
	 * @Override public PageInfo<User> queryObjectPaging(User user,Integer
	 * pageNo,Integer pageSize) { pageNo = pageNo == null?1:pageNo; pageSize =
	 * pageSize == null?10:pageSize; PageHelper.startPage(pageNo, pageSize);
	 * List<User> list = userDao.selectByUserInfo(user); //用PageInfo对结果进行包装
	 * PageInfo<User> page = new PageInfo<User>(list); //测试PageInfo全部属性
	 * System.out.println(page.getPageNum());
	 * System.out.println(page.getPageSize());
	 * System.out.println(page.getStartRow());
	 * System.out.println(page.getEndRow());
	 * System.out.println(page.getTotal()); System.out.println(page.getPages());
	 * System.out.println(page.getFirstPage());
	 * System.out.println(page.getLastPage());
	 * System.out.println(page.isHasPreviousPage());
	 * System.out.println(page.isHasNextPage()); return page; }
	 */

}
