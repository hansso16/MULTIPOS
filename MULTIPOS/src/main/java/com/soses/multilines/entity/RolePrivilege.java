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
 * The persistent class for the role_privilege database table.
 * 
 */
@Entity
@Table(name="role_privilege")
@NamedQuery(name="RolePrivilege.findAll", query="SELECT r FROM RolePrivilege r")
public class RolePrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePrivilegePK id;

	public RolePrivilege() {
	}

	public RolePrivilegePK getId() {
		return this.id;
	}

	public void setId(RolePrivilegePK id) {
		this.id = id;
	}

}