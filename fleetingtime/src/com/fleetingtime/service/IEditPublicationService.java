package com.fleetingtime.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fleetingtime.vo.Activity;
import com.fleetingtime.vo.InfoPublish;
import com.github.pagehelper.PageInfo;

public interface IEditPublicationService {
	/**
	 * 新增文本
	 * */
	public boolean addPublication(InfoPublish infoPublish);
	
	/**
	 * 首页查询当前活动相关文本
	 * */
	public JSONObject queryInfoList();
	
	/**
	 * 查询可参加的活动
	 * */
	public List<Activity> queryActityList() ;
	
	/**
	 * 用户界面查询日志列表
	 * @param  InfoPublish
	 * @return list
	 * */
	public PageInfo<InfoPublish> queryDailyList(InfoPublish infoPublish,Integer pageNo,Integer pageSize);

	/**
	 * 用户界面更新用户日志方法
	 * */
	public boolean updatePublication(InfoPublish infoPublish);
}
