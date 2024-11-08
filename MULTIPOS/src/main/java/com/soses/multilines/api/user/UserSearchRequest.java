package com.soses.multilines.api.user;

import com.soses.multilines.api.BaseSearchRequest;

/**
 * The Class UserSearchRequest.
 *
 * @author hso
 * @since Nov 8, 2024
 */
public class UserSearchRequest extends BaseSearchRequest {

	/** The username. */
	private String username;

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
}
