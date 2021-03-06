package com.fleetingtime.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fleetingtime.exception.BusinessException;
import com.fleetingtime.service.IEditPublicationService;
import com.fleetingtime.service.IVoteService;
import com.fleetingtime.vo.Activity;
import com.fleetingtime.vo.InfoPublish;
import com.fleetingtime.vo.Vote;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/publication")
public class PublicationController extends BaseController {
	@Resource
	private IVoteService voteService;
	
	@Resource
	private IEditPublicationService editPublicationService;
	
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(InfoPublish infoPublish){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if(editPublicationService.addPublication(infoPublish)){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "1000");
				map.put("errorMsg", "发表失败！");
			}
			
		} catch (BusinessException be){
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "发表失败！         错误原因：" + be.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "发表失败！         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(InfoPublish infoPublish){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if(editPublicationService.updatePublication(infoPublish)){
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "1000");
				map.put("errorMsg", "更新失败！");
			}
			
		} catch (BusinessException be){
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "更新失败！         错误原因：" + be.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "更新失败！         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/queryShare")
	public @ResponseBody Map<String, Object> queryShare(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			JSONObject repJson = editPublicationService.queryInfoList();
			
			map.put("result", SUCCESS);
			map.put("data", repJson);
			
		} catch (BusinessException be){
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "查询分享失败！         错误原因：" + be.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "查询分享失败！         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/queryActivity")
	public @ResponseBody Map<String, Object> queryActivity(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			List<Activity> list = editPublicationService.queryActityList();
			
			map.put("result", SUCCESS);
			map.put("data", list);
			
		} catch (BusinessException be){
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "查询活动失败！         错误原因：" + be.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "查询活动失败！         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/queryDailyList")
	public @ResponseBody Map<String, Object> queryDailyList(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			InfoPublish infoPublish = new InfoPublish();
			
			if(null!=request.getParameter("userId")&&!"".equals(request.getParameter("userId"))){
				infoPublish.setUserId(Integer.parseInt(request.getParameter("userId")));
			}
			if(null!=request.getParameter("infoId")){
				infoPublish.setInfoId(Integer.parseInt(request.getParameter("infoId")));
			}
			if(null!=request.getParameter("publishTitle")){
				infoPublish.setPublishTitle("'%"+request.getParameter("publishTitle")+"%'");
			}
			if(null!=request.getParameter("actId")){
				infoPublish.setActId(Integer.parseInt(request.getParameter("actId")));
			}
			if(null!=request.getParameter("keywords")){
				infoPublish.setPublishTitle("%"+request.getParameter("keywords")+"%");
				String likeList = voteService.queryList(getUserId());
				map.put("likeList", likeList);
			}
			
			PageInfo<InfoPublish> data = editPublicationService.queryDailyList(infoPublish, pageNo, pageSize);
			
			map.put("result", SUCCESS);
			map.put("pageNo", pageNo);
			map.put("total", data.getTotal());
			map.put("rows", data.getList());
			map.put("nums", data.getNavigatepageNums());
			
			
		} catch (BusinessException be) {
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "查询用户日志列表！         错误原因：" + be.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "查询用户日志列表！         错误原因：" + e.getMessage());
		}
		
		return map;
	}
	
	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping("springUpload")
	public String springUpload() throws IllegalStateException, IOException {
		
		long startTime = System.currentTimeMillis();
		
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				
				if (file != null) {
					String path = getSysCfg("uploadPath") + file.getOriginalFilename();
					
					// 上传
					file.transferTo(new File(path));
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		
		return "/success";
	}
}
