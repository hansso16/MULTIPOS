package com.soses.multilines.api.user;

import java.util.Set;

import com.soses.multilines.api.BaseRequest;

public class UserRegistrationRequest extends BaseRequest {

	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String passwordConfirmation;
	
	private Set<Integer> roleIds;
	
	private Set<Integer> privilegeIds;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Set<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public Set<Integer> getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Set<Integer> privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	@Override
	public String toString() {
		return "UserRegistrationRequest [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", passwordConfirmation=" + passwordConfirmation + ", roleIds=" + roleIds
				+ ", privilegeIds=" + privilegeIds + "]";
	}

	
}
