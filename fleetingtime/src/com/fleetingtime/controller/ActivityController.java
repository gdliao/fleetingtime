package com.fleetingtime.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fleetingtime.exception.BusinessException;
import com.fleetingtime.service.IVoteService;
import com.fleetingtime.vo.Vote;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {
	
	@Resource
	private IVoteService voteService;
	
	@RequestMapping("/like")
	public  @ResponseBody Map<String, Object> like(Vote vote){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			if(voteService.like(vote)){
				updateLikeList();
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				
			}else{
				map.put("result", ERROR);
				map.put("errorCode", "2000");
				map.put("errorMsg", "不可以重复投票。");
			}
			
		} catch (BusinessException be){
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "投票失败！         错误原因：" + be.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "投票失败！         错误原因：" + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/cancelVote")
	public  @ResponseBody Map<String, Object> cancelVote(Vote vote){
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			if(voteService.cancel(vote)){
				
				map.put("result", SUCCESS);
				map.put("errorCode", "0000");
				
			}
			
		} catch (BusinessException be){
			be.printStackTrace();
			
			map.put("result", ERROR);
			map.put("errorCode", be.getRetCode());
			map.put("errorMsg", "取消失败！         错误原因：" + be.getRetMsg());
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", ERROR);
			map.put("errorCode", "9999");
			map.put("errorMsg", "取消失败！         错误原因：" + e.getMessage());
		}
		return map;
	}
}
