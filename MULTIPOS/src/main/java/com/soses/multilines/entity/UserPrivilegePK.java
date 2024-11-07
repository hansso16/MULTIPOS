package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The primary key class for the user_privilege database table.
 * 
 */
@Embeddable
public class UserPrivilegePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USER_ID", unique=true, nullable=false)
	private int userId;

	@Column(name="PRIVILEGE_ID", unique=true, nullable=false)
	private int privilegeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ASSIGNED_TIMESTAMP", unique=true, nullable=false)
	private LocalDateTime assignedTimestamp;

	public UserPrivilegePK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPrivilegeId() {
		return this.privilegeId;
	}
	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
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
		if (!(other instanceof UserPrivilegePK)) {
			return false;
		}
		UserPrivilegePK castOther = (UserPrivilegePK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.privilegeId == castOther.privilegeId)
			&& this.assignedTimestamp.equals(castOther.assignedTimestamp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.privilegeId;
		hash = hash * prime + this.assignedTimestamp.hashCode();
		
		return hash;
	}
}