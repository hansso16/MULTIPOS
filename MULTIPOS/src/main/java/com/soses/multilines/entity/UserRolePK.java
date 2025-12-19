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

	@Column(name="USER_ID", unique=true, nullable=false)
	private int userId;

	@Column(name="ROLE_ID", unique=true, nullable=false)
	private int roleId;

	public UserRolePK() {
	}

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRolePK [userId=" + userId + ", roleId=" + roleId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, userId);
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
		return roleId == other.roleId && userId == other.userId;
	}

	
}