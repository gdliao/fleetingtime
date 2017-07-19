package com.fleetingtime.dao;

import com.fleetingtime.vo.ActPublish;

public interface IActPublishDao {
    int insert(ActPublish record);

    int insertSelective(ActPublish record);
    
    int updateByInfoId(ActPublish ap);
    
    int deleteByInfoId(ActPublish ap);
}