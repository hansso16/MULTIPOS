package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
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
		return assignedTimestamp;
	}

	public void setAssignedTimestamp(LocalDateTime assignedTimestamp) {
		this.assignedTimestamp = assignedTimestamp;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPrivilegePK other = (UserPrivilegePK) obj;
		return Objects.equals(assignedTimestamp, other.assignedTimestamp) && privilegeId == other.privilegeId
				&& userId == other.userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assignedTimestamp, privilegeId, userId);
	}
}