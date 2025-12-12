package com.soses.multilines.api;

import com.soses.multilines.dto.ErrorPageDTO;

/**
 * The Class BaseResponse.
 *
 * @author hso
 * @since 17 Nov 2021
 */
public class BaseResponse {

	/** The error. */
	private ErrorPageDTO error;

	/** The response message. */
	private String responseMessage;
	
	/**
	 * Gets the response message.
	 *
	 * @return the response message
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * Sets the response message.
	 *
	 * @param responseMessage the new response message
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public ErrorPageDTO getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(ErrorPageDTO error) {
		this.error = error;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "BaseResponse [error=" + error + "]";
	}
	
}
