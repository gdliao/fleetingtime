package com.meeting.vo;

import java.util.Date;

public class Msg {
    private Integer msgId;

    private Integer msgTargetUserId;

    private String msgContent;

    private Integer msgStatus;
    
    private Integer msgFromUserId;
    
    private Date msgAddTime;
    
    private Integer userId;

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getMsgAddTime() {
		return msgAddTime;
	}

	public void setMsgAddTime(Date msgAddTime) {
		this.msgAddTime = msgAddTime;
	}

	public Integer getMsgFromUserId() {
		return msgFromUserId;
	}

	public void setMsgFromUserId(Integer msgFromUserId) {
		this.msgFromUserId = msgFromUserId;
	}

	public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgTargetUserId() {
        return msgTargetUserId;
    }

    public void setMsgTargetUserId(Integer msgTargetUserId) {
        this.msgTargetUserId = msgTargetUserId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }
}