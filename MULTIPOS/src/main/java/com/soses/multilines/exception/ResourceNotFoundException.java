package com.soses.multilines.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ResourceNotFoundException.
 *
 * @author hso
 * @since Nov 8, 2024
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1167548622677632492L;

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message the message
	 */
	public ResourceNotFoundException(String message) {
        super(message);
    }
	
	/**
	 * Instantiates a new next gen exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
	/**
	 * Instantiates a new next gen exception.
	 *
	 * @param cause the cause
	 */
	public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
