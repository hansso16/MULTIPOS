package com.soses.multilines.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the user_role database table.
 * 
 */
@Embeddable
public class UserRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USER_CODE", unique=true, nullable=false)
	private int userCode;

	@Column(name="ROLE_ID", unique=true, nullable=false)
	private int roleId;

	public UserRolePK() {
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRolePK [userCode=" + userCode + ", roleId=" + roleId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, userCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRolePK other = (UserRolePK) obj;
		return roleId == other.roleId && userCode == other.userCode;
	}

	
}