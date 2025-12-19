package com.soses.multilines.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {

	private int userId;

	private LocalDateTime entryTimestamp;

	private String password;

	private LocalDate terminationDate;

	private LocalDateTime updateTimestamp;

	private String userCode;

	private String username;
	
	private String lastName;
	
	private String firstName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getEntryTimestamp() {
		return entryTimestamp;
	}

	public void setEntryTimestamp(LocalDateTime entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}

	public LocalDateTime getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", entryTimestamp=" + entryTimestamp + ", password=" + password
				+ ", terminationDate=" + terminationDate + ", updateTimestamp=" + updateTimestamp + ", userCode="
				+ userCode + ", username=" + username + ", lastName=" + lastName + ", firstName=" + firstName + "]";
	}
	
}
