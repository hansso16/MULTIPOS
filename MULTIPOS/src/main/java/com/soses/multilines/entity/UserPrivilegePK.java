package com.soses.multilines.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPrivilegePK other = (UserPrivilegePK) obj;
		return privilegeId == other.privilegeId && userId == other.userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(privilegeId, userId);
	}
}