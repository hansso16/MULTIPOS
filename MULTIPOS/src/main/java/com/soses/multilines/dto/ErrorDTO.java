package com.soses.multilines.dto;

/**
 * The Class ErrorDTO.
 *
 * @author hso
 * @since Nov 19, 2024
 */
public class ErrorDTO {

	/** The error code. */
	String errorCode;
	
	/** The error message. */
	String errorMessage;

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ErrorDTO [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
}
