package com.meeting.vo;

import java.util.List;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private String email;

    private String phoneNumber;
    
    private List<Integer> userRoleList;

    public List<Integer> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<Integer> userRoleList) {
		this.userRoleList = userRoleList;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }
}