package com.soses.multilines.api;

public class BaseRequest {

	private int userId;
	
	private String userCode;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	public String toString() {
		return "BaseRequest [userId=" + userId + ", userCode=" + userCode + "]";
	}
	
}
