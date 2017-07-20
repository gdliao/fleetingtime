package com.meeting.dao;

import java.util.List;

import com.meeting.vo.Function;

public interface FunctionDao {
    int insert(Function record);

    int insertSelective(Function record);
    
    Function selectByFunctionInfo(Function func);
    
    List<Function> selectFunctionList(Function func);
}