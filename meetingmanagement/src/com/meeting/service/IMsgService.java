package com.meeting.service;

import java.util.List;

import com.meeting.vo.Msg;
import com.meeting.vo.User;

public interface IMsgService extends IBaseService<Msg>{
	/**
	 * 用户消息列表
	 * */
	public List<Msg> queryMsgListByUser(User user);
	
	/**
	 * 消息回复服务
	 * */
	public boolean replyMsg(Msg msg);
}
