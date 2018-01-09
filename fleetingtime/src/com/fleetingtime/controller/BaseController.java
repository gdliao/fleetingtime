package com.fleetingtime.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.fleetingtime.constant.Constants;
import com.fleetingtime.dao.IActivityDao;
import com.fleetingtime.service.IVoteService;
import com.fleetingtime.utils.SystemCfg;
import com.fleetingtime.vo.Activity;
import com.fleetingtime.vo.Vote;

public class BaseController<E> implements Constants{

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected int pageNo;
	protected int pageSize;
	
	@Resource
	private IVoteService voteService;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.pageNo = (request.getParameter("page")== null||request.getParameter("page").equals("")) ? 1 :Integer.parseInt(request.getParameter("page"));
		this.pageSize = (request.getParameter("rows")== null||request.getParameter("rows").equals("")) ? 10 :Integer.parseInt(request.getParameter("rows"));
	}

	public Integer getUserId() {
		Integer userId = session.getAttribute("userId") == null ? null
				: Integer.parseInt(session.getAttribute("userId").toString());
		return userId;
	}
	
	public void updateMemberStatus(String s){
		session.removeAttribute("memberStatus");
		session.setAttribute("memberStatus", s);
	}
	
	public void updateLikeList(){
		
		String likeList = voteService.queryList(getUserId());
		
		session.removeAttribute("likeList");
		session.setAttribute("likeList",likeList);

	}
	public String getLikeList(){
		String list = session.getAttribute("likeList")== null ? null
				: session.getAttribute("likeList").toString();
		return list;
	}
	
	public String getSysCfg(String str){
		
		SystemCfg sysCfg = new SystemCfg();
		
		String resStr = sysCfg.get(str);
		
		return resStr;
	}
}
