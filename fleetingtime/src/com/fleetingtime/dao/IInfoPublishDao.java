package com.fleetingtime.dao;

import java.util.List;

import com.fleetingtime.vo.InfoPublish;

public interface IInfoPublishDao {
    int deleteByPrimaryKey(Integer infoId);

    int insert(InfoPublish record);

    int insertSelective(InfoPublish record);

    InfoPublish selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(InfoPublish record);

    int updateByPrimaryKeyWithBLOBs(InfoPublish record);

    int updateByPrimaryKey(InfoPublish record);
    
    List<InfoPublish> selectByObject(InfoPublish infoPublish);
    
    List<InfoPublish> selectByObjectRandom(InfoPublish infoPublish);
    
}