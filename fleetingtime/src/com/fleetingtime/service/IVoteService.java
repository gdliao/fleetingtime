package com.fleetingtime.service;

import java.util.List;

import com.fleetingtime.vo.Vote;

public interface IVoteService {
	/**
	 * 用户首页点赞方法
	 * */
	public boolean like(Vote vote);
	/**
	 * 用户首页取消点赞方法
	 * */
	public boolean cancel(Vote vote);
	
	/**
	 * 登录后 查询用户所有已赞的list
	 * */
	public String queryList(int userId);
}
