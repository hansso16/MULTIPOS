package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@Column(name="SHORT_NAME", length=20)
	private String shortName;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private LocalDate startLocalDate;
	
	@ManyToMany
    @JoinTable(
        name = "USER_ROLE",
        joinColumns = @JoinColumn(name = "ROLE_ID"),
        inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> userSet = new HashSet<>();
	
	@OneToMany
	@JoinColumn(name="ROLE_ID")
	private SortedSet<Privilege> privilegeSet = new TreeSet<>();

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public LocalDate getEndLocalDate() {
		return this.endLocalDate;
	}

	public void setEndLocalDate(LocalDate endLocalDate) {
		this.endLocalDate = endLocalDate;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public LocalDate getStartLocalDate() {
		return this.startLocalDate;
	}

	public void setStartLocalDate(LocalDate startLocalDate) {
		this.startLocalDate = startLocalDate;
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	public SortedSet<Privilege> getPrivilegeSet() {
		return privilegeSet;
	}

	public void setPrivilegeSet(SortedSet<Privilege> privilegeSet) {
		this.privilegeSet = privilegeSet;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}