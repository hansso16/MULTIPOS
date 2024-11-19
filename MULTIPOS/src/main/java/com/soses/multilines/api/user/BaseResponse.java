package com.soses.multilines.api.user;

import com.soses.multilines.api.ErrorResponse;

public class BaseResponse extends ErrorResponse {

	private String responseMessage;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "BaseResponse [responseMessage=" + responseMessage + "]";
	}
	
}
