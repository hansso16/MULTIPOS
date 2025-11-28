package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_ID", unique=true, nullable=false)
	private Integer roleId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private LocalDate endLocalDate;

	@Column(name="ROLE_DESCRIPTION", length=50)
	private String roleDescription;

	@Column(name="ROLE_NAME", length=20)
	private String roleName;

	@Column(name="ROLE_SHORT_NAME", length=20)
	private String shortName;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private LocalDate startLocalDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_privilege",
        joinColumns = @JoinColumn(name = "ROLE_ID"),
        inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Set<Privilege> privilegeSet = new HashSet<>();
	
	@ManyToMany(mappedBy = "roleSet")
	private Set<User> userSet;

	public Role() {
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

	public Set<Privilege> getPrivilegeSet() {
		return privilegeSet;
	}

	public void setPrivilegeSet(Set<Privilege> privilegeSet) {
		this.privilegeSet = privilegeSet;
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", endLocalDate=" + endLocalDate + ", roleDescription=" + roleDescription
				+ ", roleName=" + roleName + ", shortName=" + shortName + ", startLocalDate=" + startLocalDate
				+ ", privilegeSet=" + privilegeSet + ", userSet=" + userSet + "]";
	}

}