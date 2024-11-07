package com.soses.multilines.entity;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the role_privilege database table.
 * 
 */
@Embeddable
public class RolePrivilegePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ID", unique=true, nullable=false)
	private int roleId;

	@Column(name="PRIVILEGE_ID", unique=true, nullable=false)
	private int privilegeId;

	public RolePrivilegePK() {
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getPrivilegeId() {
		return this.privilegeId;
	}
	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePrivilegePK)) {
			return false;
		}
		RolePrivilegePK castOther = (RolePrivilegePK)other;
		return 
			(this.roleId == castOther.roleId)
			&& (this.privilegeId == castOther.privilegeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.privilegeId;
		
		return hash;
	}
}