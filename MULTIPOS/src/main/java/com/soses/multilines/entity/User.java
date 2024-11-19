package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID", unique=true, nullable=false)
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_TIMESTAMP")
	private LocalDateTime entryTimestamp;

	@Column(length=100)
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="TERMINATION_DATE")
	private LocalDate terminationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIMESTAMP")
	private LocalDateTime updateTimestamp;

	@Column(name="USER_CODE", length=10)
	private String userCode;

	@Column(name="USERNAME", length=20)
	private String username;
	
	@Column(name="LAST_NAME", length=20)
	private String lastName;
	
	@Column(name="FIRST_NAME", length=50)
	private String firstName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	        name = "user_role",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
    private Set<Role> roleSet= new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_privilege", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<Privilege> privileges;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getEntryTimestamp() {
		return this.entryTimestamp;
	}

	public void setEntryTimestamp(LocalDateTime entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}

	public LocalDateTime getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", entryTimestamp=" + entryTimestamp + ", password=" + password
				+ ", terminationDate=" + terminationDate + ", updateTimestamp=" + updateTimestamp + ", userCode="
				+ userCode + ", username=" + username + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", roleSet=" + roleSet + ", privileges=" + privileges + "]";
	}

}