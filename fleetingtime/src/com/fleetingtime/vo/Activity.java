package com.fleetingtime.vo;

import java.util.Date;

public class Activity {
    private Integer actId;

    private String actName;

    private Date startTime;

    private Date endTime;
    
    private Integer wordLimit;

    public Integer getWordLimit() {
		return wordLimit;
	}

	public void setWordLimit(Integer wordLimit) {
		this.wordLimit = wordLimit;
	}

	public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName == null ? null : actName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}