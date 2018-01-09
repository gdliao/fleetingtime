package com.meeting.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meeting.dao.MsgDao;
import com.meeting.exception.BusinessException;
import com.meeting.service.IMsgService;
import com.meeting.vo.Msg;
import com.meeting.vo.User;

@Transactional
@Service("msgService")
public class MsgServiceImpl implements IMsgService {
	
	@Resource
	private MsgDao msgDao;
	@Override
	public boolean insert(Msg t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Msg t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Msg t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count(Msg t) {
		
		return msgDao.countMsg(t);
	}

	@Override
	public List<Msg> queryMsgListByUser(User user) {
		Msg msg = new Msg();
		msg.setMsgTargetUserId(user.getUserId());
		return msgDao.queryMsgListByMsg(msg);
	}

	@Override
	public boolean replyMsg(Msg msg) {
		
		try {
			int userId = msg.getUserId();//用户编码
			int msgId = msg.getMsgId();//回复对象的消息编码
			String msgContent = msg.getMsgContent();//消息内容
			
			Msg t = new Msg();
			t.setMsgId(msgId);
			
			Msg message = new Msg();
			message.setMsgAddTime(new Date());
			message.setMsgContent(msgContent);
			message.setMsgFromUserId(userId);//用户编码
			message.setMsgStatus(1);
			message.setMsgTargetUserId(msgDao.queryByT(t).getMsgFromUserId());
		
			msgDao.insert(message);
			
			return true;
		} catch (Exception e) {
			throw new BusinessException("1001", "服务层业务异常："+e.getMessage());
		}
		
		
	}

}
