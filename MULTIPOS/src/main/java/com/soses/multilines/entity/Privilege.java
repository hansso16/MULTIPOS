package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the privilege database table.
 * 
 */
@Entity
@Table(name="privilege")
@NamedQuery(name="Privilege.findAll", query="SELECT p FROM Privilege p")
public class Privilege implements Serializable, Comparable<Privilege> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRIVILEGE_ID", unique=true, nullable=false)
	private int privilegeId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private LocalDate endLocalDate;

	@Column(name="PRIVILEGE_DESCRIPTION", length=50)
	private String privilegeDescription;

	@Column(name="PRIVILEGE_NAME", nullable=false, length=30)
	private String privilegeName;

	@Column(name="SHORT_NAME", nullable=false, length=20)
	private String shortName;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private LocalDate startLocalDate;
	
	@Column(name="ROLE_ID")
	private int roleId;


	public Privilege() {
	}

	public int getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public LocalDate getEndLocalDate() {
		return this.endLocalDate;
	}

	public void setEndLocalDate(LocalDate endLocalDate) {
		this.endLocalDate = endLocalDate;
	}

	public String getPrivilegeDescription() {
		return this.privilegeDescription;
	}

	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}

	public String getPrivilegeName() {
		return this.privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public LocalDate getStartLocalDate() {
		return this.startLocalDate;
	}

	public void setStartLocalDate(LocalDate startLocalDate) {
		this.startLocalDate = startLocalDate;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public int compareTo(Privilege o) {
		return this.shortName.compareToIgnoreCase(o.shortName);
	}

}