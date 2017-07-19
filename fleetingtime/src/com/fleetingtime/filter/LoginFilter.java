package com.fleetingtime.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fleetingtime.utils.StringUtils;

public class LoginFilter implements Filter{

	private List<String> exceptURIList = null;

	protected Log logger = LogFactory.getLog(this.getClass());

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

			chain.doFilter(request, response);
			return;
		} else {// 未登陆
			String loginURL = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/index.html";
			response.sendRedirect(loginURL);//+ "?isSessionExpired="+new Random().nextInt()
			return;
		}

	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
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
