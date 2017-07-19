package com.fleetingtime.vo;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String phoneNum;

    private String email;

    private String password;

    private Date createTime;
    
    private String memberStatus;

    private Date endTime;
    
    private Double capacityTotle;
    
    private Double capacityCurrent;
    

    public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Double getCapacityTotle() {
		return capacityTotle;
	}

	public void setCapacityTotle(Double capacityTotle) {
		this.capacityTotle = capacityTotle;
	}

	public Double getCapacityCurrent() {
		return capacityCurrent;
	}

	public void setCapacityCurrent(Double capacityCurrent) {
		this.capacityCurrent = capacityCurrent;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}