package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	private Integer privilegeId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private LocalDate endLocalDate;

	@Column(name="PRIVILEGE_DESCRIPTION", length=50)
	private String privilegeDescription;

	@Column(name="PRIVILEGE_NAME", nullable=false, length=30)
	private String privilegeName;

	@Column(name="PRIVILEGE_SHORT_NAME", nullable=false, length=20)
	private String shortName;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private LocalDate startLocalDate;
	
	@ManyToMany(mappedBy = "privilegeSet")
    private Set<Role> roles;


	public Privilege() {
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}


	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}


	public LocalDate getEndLocalDate() {
		return endLocalDate;
	}


	public void setEndLocalDate(LocalDate endLocalDate) {
		this.endLocalDate = endLocalDate;
	}


	public String getPrivilegeDescription() {
		return privilegeDescription;
	}


	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}


	public String getPrivilegeName() {
		return privilegeName;
	}


	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
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


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public int compareTo(Privilege o) {
		return this.shortName.compareToIgnoreCase(o.shortName);
	}

}