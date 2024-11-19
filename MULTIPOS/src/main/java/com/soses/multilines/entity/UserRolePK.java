package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ASSIGNED_TIMESTAMP", unique=true, nullable=false)
	private LocalDateTime assignedTimestamp;

	public UserRolePK() {
	}
	
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public LocalDateTime getAssignedTimestamp() {
		return this.assignedTimestamp;
	}
	public void setAssignedTimestamp(LocalDateTime assignedTimestamp) {
		this.assignedTimestamp = assignedTimestamp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRolePK)) {
			return false;
		}
		UserRolePK castOther = (UserRolePK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.roleId == castOther.roleId)
			&& this.assignedTimestamp.equals(castOther.assignedTimestamp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.assignedTimestamp.hashCode();
		
		return hash;
	}
}