package com.meeting.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.meeting.constant.Constants;

public class BaseController<E> implements Constants{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected int pageNo;
	protected int pageSize;
	
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
	
}
