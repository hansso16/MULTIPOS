package com.soses.multilines.api;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * The Class ErrorResponse.
 *
 * @author hso
 * @since Nov 8, 2024
 */
public class ErrorResponse {

	/** The error code. */
	private String errorCode;
	
	/** The error message. */
	private String errorMessage;
	
	/** The throwable. */
	private Throwable throwable;
	
	/** The http status. */
	private HttpStatus httpStatus;
	
	/** The timestamp. */
	private LocalDateTime timestamp;

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
	 * Gets the throwable.
	 *
	 * @return the throwable
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * Sets the throwable.
	 *
	 * @param throwable the new throwable
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the http status.
	 *
	 * @param httpStatus the new http status
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ErrorResponse [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", throwable=" + throwable
				+ ", httpStatus=" + httpStatus + ", timestamp=" + timestamp + "]";
	}

	/**
	 * Instantiates a new error response.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param throwable the throwable
	 * @param httpStatus the http status
	 * @param timestamp the timestamp
	 */
	public ErrorResponse(String errorCode, String errorMessage, Throwable throwable, HttpStatus httpStatus,
			LocalDateTime timestamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

	/**
	 * Instantiates a new error response.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param throwable the throwable
	 */
	public ErrorResponse(String errorCode, String errorMessage, Throwable throwable) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.throwable = throwable;
	}

	/**
	 * Instantiates a new error response.
	 *
	 * @param errorMessage the error message
	 */
	public ErrorResponse(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	/**
	 * Instantiates a new error response.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	public ErrorResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * Instantiates a new error response.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param httpStatus the http status
	 * @param timestamp the timestamp
	 */
	public ErrorResponse(String errorCode, String errorMessage, HttpStatus httpStatus, LocalDateTime timestamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

}
