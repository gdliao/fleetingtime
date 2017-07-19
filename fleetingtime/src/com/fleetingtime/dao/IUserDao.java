package com.fleetingtime.dao;

import java.util.List;

import com.fleetingtime.vo.User;

public interface IUserDao{
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    
    List<User> selectAllUser();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectByUserInfo(User user);
}
