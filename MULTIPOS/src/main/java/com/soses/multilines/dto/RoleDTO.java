package com.soses.multilines.dto;

import java.time.LocalDate;

public class RoleDTO {

	private Integer roleId;

	private LocalDate endLocalDate;

	private String roleDescription;

	private String roleName;

	private String shortName;
	
	private boolean assigned = false;

	private LocalDate startLocalDate;

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public LocalDate getEndLocalDate() {
		return endLocalDate;
	}

	public void setEndLocalDate(LocalDate endLocalDate) {
		this.endLocalDate = endLocalDate;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public LocalDate getStartLocalDate() {
		return startLocalDate;
	}

	public void setStartLocalDate(LocalDate startLocalDate) {
		this.startLocalDate = startLocalDate;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", endLocalDate=" + endLocalDate + ", roleDescription=" + roleDescription
				+ ", roleName=" + roleName + ", shortName=" + shortName + ", assigned=" + assigned + ", startLocalDate="
				+ startLocalDate + "]";
	}
}
