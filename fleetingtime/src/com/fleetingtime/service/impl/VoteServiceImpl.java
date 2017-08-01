package com.fleetingtime.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleetingtime.dao.IActivityDao;
import com.fleetingtime.dao.IVoteDao;
import com.fleetingtime.exception.BusinessException;
import com.fleetingtime.service.IVoteService;
import com.fleetingtime.vo.Activity;
import com.fleetingtime.vo.Vote;

@Transactional
@Service("voteService")
public class VoteServiceImpl implements IVoteService {
	
	@Resource
	private IVoteDao voteDao;
	@Resource
	private IActivityDao activityDao;
	
	@Override
	public boolean like(Vote vote) {
		try {
			voteDao.insert(vote);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("1000", e.getMessage());
		}
	}

	@Override
	public String queryList(int userId) {
		
		Activity act = activityDao.selectByThisTime();
		
		Vote vote = new Vote();
		vote.setUserId(userId);
		vote.setActId(act.getActId());
		
		List<Vote> list = voteDao.selectByInfo(vote);
		StringBuffer likeList = new StringBuffer();
		for(int i=0;i<list.size();i++){
			if(i!=0){
				likeList.append(",");
			}
			likeList.append(list.get(i).getInfoId());
		}
		
		return likeList.toString();
	}

	@Override
	public boolean cancel(Vote vote) {
		try {
			voteDao.delete(vote);
			return true;
		} catch (Exception e) {
			throw new BusinessException("1000", e.getMessage());
		}
	}

}
