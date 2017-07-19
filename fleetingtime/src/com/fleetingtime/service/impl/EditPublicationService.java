package com.fleetingtime.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fleetingtime.dao.IActPublishDao;
import com.fleetingtime.dao.IActivityDao;
import com.fleetingtime.dao.IInfoPublishDao;
import com.fleetingtime.dao.ISequenceDao;
import com.fleetingtime.exception.BusinessException;
import com.fleetingtime.service.IEditPublicationService;
import com.fleetingtime.vo.ActPublish;
import com.fleetingtime.vo.Activity;
import com.fleetingtime.vo.InfoPublish;
import com.fleetingtime.vo.Sequence;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Transactional
@Service("editPublicationService")
public class EditPublicationService implements IEditPublicationService {
	
	@Resource
	private IInfoPublishDao infoPublishDao;
	@Resource
	private IActPublishDao actPublishDao;
	@Resource
	private IActivityDao activityDao;
	@Resource
	private ISequenceDao sequenceDao;
	
	@Override
	public boolean addPublication(InfoPublish infoPublish) {
		try {
			
			Sequence infoSeq = new Sequence();
			infoSeq.setName("seq_info_id");
			infoSeq = sequenceDao.selectNextval(infoSeq);
			String infoId = infoSeq.getNextval();
			
			infoPublish.setInfoId(Integer.parseInt(infoId));
			infoPublish.setPublishTime(new Date());
			
			if(infoPublish.getIfShare().equals("1")){
				
				ActPublish ap = new ActPublish();
				ap.setActId(infoPublish.getActId());
				ap.setInfoId(Integer.parseInt(infoId));
				actPublishDao.insert(ap);
			}
			
			infoPublishDao.insert(infoPublish);
			
			return true;
		} catch (Exception e) {
			throw new BusinessException("1000", e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public boolean updatePublication(InfoPublish infoPublish){
		
		try{
			infoPublish.setPublishTime(new Date());
			ActPublish ap = new ActPublish();
			ap.setActId(infoPublish.getActId());
			ap.setInfoId(infoPublish.getInfoId());
			if(infoPublish.getIfShare().equals("1")){
				actPublishDao.updateByInfoId(ap);
			}else{
				actPublishDao.deleteByInfoId(ap);
			}
			infoPublishDao.updateByPrimaryKeyWithBLOBs(infoPublish);
			return true;
		} catch(Exception e){
			throw new BusinessException("1000",e.getMessage());
		}
		
	}
	
	@Override
	public JSONObject queryInfoList() {
		try {
			JSONObject json = new JSONObject();
			
			Activity act = activityDao.selectByThisTime();
			String subject = act.getActName();
			
			
			InfoPublish infoPublish = new InfoPublish();
			infoPublish.setIfShare("1");
			infoPublish.setActId(act.getActId());
			List<InfoPublish> list = infoPublishDao.selectByObjectRandom(infoPublish);
			
			json.put("list", list);
			json.put("subject", subject);
			return json;
		} catch (Exception e) {
			throw new BusinessException("1000", e.getMessage());
		}
		
	}

	@Override
	public List<Activity> queryActityList() {
		
		return activityDao.selectEffectiveAct();
	}

	@Override
	public PageInfo<InfoPublish> queryDailyList(InfoPublish infoPublish,Integer pageNo,Integer pageSize) {
		
		try {
			
			pageNo = pageNo == null?1:pageNo;
		    pageSize = pageSize == null?10:pageSize;
		    PageHelper.startPage(pageNo, pageSize);
		    
		    if(null!=infoPublish.getPublishTitle()&&!"".equals(infoPublish.getPublishTitle())){
		    	infoPublish.setIfShare("1");
		    }
			List<InfoPublish> list = infoPublishDao.selectByObject(infoPublish);
			
			PageInfo<InfoPublish> page = new PageInfo<InfoPublish>(list);
			
			return page;
		} catch (Exception e) {
			throw new BusinessException("1000", e.getMessage());
		}
	}
	
	
}
