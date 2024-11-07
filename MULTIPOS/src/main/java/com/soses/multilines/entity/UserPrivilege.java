package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the user_privilege database table.
 * 
 */
@Entity
@Table(name="user_privilege")
@NamedQuery(name="UserPrivilege.findAll", query="SELECT u FROM UserPrivilege u")
public class UserPrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserPrivilegePK id;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private LocalDate endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private LocalDate startDate;

	public UserPrivilege() {
	}

	public UserPrivilegePK getId() {
		return this.id;
	}

	public void setId(UserPrivilegePK id) {
		this.id = id;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}