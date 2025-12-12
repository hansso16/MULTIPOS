package com.soses.multilines.dto;

/**
 * The Class ErrorPageDTO.
 *
 * @author hso
 * @since 17 Nov 2021
 */
public class ErrorPageDTO {

	/** The error message. */
	private String message;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ErrorPageDTO [message=" + message + "]";
	}

}
