package com.fleetingtime.dao;

import java.util.List;

import com.fleetingtime.vo.Activity;

public interface IActivityDao {
    int deleteByPrimaryKey(Integer actId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer actId);
    
    Activity selectByThisTime();

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    List<Activity> selectEffectiveAct();
}