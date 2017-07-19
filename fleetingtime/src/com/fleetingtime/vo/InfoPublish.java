package com.fleetingtime.vo;

import java.util.Date;

public class InfoPublish {
    private Integer infoId;

    private Integer userId;

    private Date publishTime;

    private String publishTitle;

    private String publishLocation;

    private String infoDes;
    
    private String ifShare;
    
    private Integer likeTotle;
    
    private Integer actId;
    
	public Integer getLikeTotle() {
		return likeTotle;
	}

	public void setLikeTotle(Integer likeTotle) {
		this.likeTotle = likeTotle;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getIfShare() {
		return ifShare;
	}

	public void setIfShare(String ifShare) {
		this.ifShare = ifShare;
	}

	public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishTitle() {
        return publishTitle;
    }

    public void setPublishTitle(String publishTitle) {
        this.publishTitle = publishTitle == null ? null : publishTitle.trim();
    }

    public String getPublishLocation() {
        return publishLocation;
    }

    public void setPublishLocation(String publishLocation) {
        this.publishLocation = publishLocation == null ? null : publishLocation.trim();
    }

    public String getInfoDes() {
        return infoDes;
    }

    public void setInfoDes(String infoDes) {
        this.infoDes = infoDes == null ? null : infoDes.trim();
    }
}