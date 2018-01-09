package com.meeting.dao;

import java.util.List;

import com.meeting.vo.Msg;

public interface MsgDao extends BaseDao<Msg>{
    int insert(Msg record);

    int insertSelective(Msg record);
    
    int countMsg(Msg record);
    
    List<Msg> queryMsgListByMsg(Msg record);
}