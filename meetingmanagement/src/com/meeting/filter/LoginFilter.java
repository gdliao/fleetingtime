package com.meeting.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.meeting.exception.BusinessException;
import com.meeting.service.IFilterService;
import com.meeting.service.impl.FilterServiceImpl;
import com.meeting.utils.StringUtils;

public class LoginFilter implements Filter{

	private List<String> exceptURIList = null;

	protected Log logger = LogFactory.getLog(this.getClass());
	
	private IFilterService filterService;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getRequestURI();
		// 特殊URI，不需要登陆校验
		if (StringUtils.isEmpty(uri) || isAnExceptionalURL(uri)) {
			chain.doFilter(req, resp);
			return;
		}
		uri = uri.substring(uri.lastIndexOf("/") + 1);

		String param = "";
		if (request.getQueryString() != null) {
			param = new String(request.getQueryString().getBytes("iso8859-1"),
					"UTF-8");
		}
		if (param != null && !"".equals(param)) {
			uri = uri + "?" + param;
		}

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		
		if (session != null
				&& username != null) {
			logger.info("> loginFilter :::: 已经登录，loginNo="
					+ username);
			
			//过滤
			if(null!=session.getAttribute("roles")&&!session.getAttribute("roles").equals("")){
				JSONObject reqJson = new JSONObject();
				reqJson.put("roles", session.getAttribute("roles"));
				reqJson.put("url", request.getRequestURI());
				/*if(!filterService.filteration(reqJson)){
					session.invalidate();
					throw new BusinessException("2000","无权限进行此操作");
				}*/
				System.out.println(1111);
			}

			chain.doFilter(request, response);
			return;
		} else {// 未登陆
			String loginURL = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/login.jsp";
			response.sendRedirect(loginURL+ "?isSessionExpired="+new Random().nextInt());
			return;
		}

	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		

		ServletContext sc = cfg.getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(sc);

		if (cxt != null && cxt.getBean("filterService") != null && filterService == null)
			filterService = (FilterServiceImpl) cxt.getBean("filterService");
		
		String exceptURIs = cfg.getInitParameter("except");
		if (StringUtils.isNotEmpty(exceptURIs)) {
			exceptURIList = Arrays.asList(exceptURIs.split(","));
		}
	}

	/**
	 * 判读url是否是一个跳过登录校验的URL
	 * 
	 * @param url
	 * @return
	 */
	private boolean isAnExceptionalURL(String url) {
		boolean isAnExceptionURL = false;
		if (exceptURIList != null && exceptURIList.size() > 0) {
			for (String exceptURI : exceptURIList) {
				if (url.indexOf(exceptURI) != -1) {
					isAnExceptionURL = true;
					break;
				}
			}
		}
		return isAnExceptionURL;
	}

}
