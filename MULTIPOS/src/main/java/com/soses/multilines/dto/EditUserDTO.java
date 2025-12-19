package com.soses.multilines.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditUserDTO {

	private Integer userId;
	
	private String userCode;
	private String username; // readonly
    private String firstName;
    private String lastName;

    private boolean enabled;
    private LocalDate terminationDate;

    // optional password change
    private String newPassword;
    private String confirmPassword;

    private List<Integer> roleIds = new ArrayList<>();
    private List<Integer> privilegeIds = new ArrayList<>();
    
    
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public LocalDate getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	public List<Integer> getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(List<Integer> privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	@Override
	public String toString() {
		return "EditUserDTO [userId=" + userId + ", userCode=" + userCode + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", enabled=" + enabled + ", terminationDate=" + terminationDate
				+ ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + ", roleIds=" + roleIds
				+ ", privilegeIds=" + privilegeIds + "]";
	}
}
