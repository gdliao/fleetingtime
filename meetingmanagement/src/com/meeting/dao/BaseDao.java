package com.meeting.dao;

import java.util.List;

public interface BaseDao<T> {
	
	int insert(T t);

	int insertSelective(T t);

	int countMsg(T t);

	List<T> queryTListByT(T t);

	T queryByT(T t);
}
