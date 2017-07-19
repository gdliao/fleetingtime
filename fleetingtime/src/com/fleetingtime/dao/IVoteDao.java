package com.fleetingtime.dao;

import java.util.List;

import com.fleetingtime.vo.Vote;

public interface IVoteDao {
    int insert(Vote record);
    
    int delete(Vote record);

    int insertSelective(Vote record);
    
    List<Vote> selectByInfo(Vote vote);
}