package com.soses.multilines.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="agent")
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable {

	private static final long serialVersionUID = 7068477217909774168L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AGENT_ID", unique=true, nullable=false)
	private int agentId;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true,
        foreignKey = @ForeignKey(name = "AGENT_user_FK")
    )
	private User user;

	@Column(name="AGENT_CODE", nullable=false, length=10)
	private String agentCode;
	
	@Column(name="USER_ID", nullable=false)
	private Integer userId;
	
	@Column(name="IS_ACTIVE", nullable=false)
	private boolean isActive;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "agent_customer",
        joinColumns = @JoinColumn(name = "AGENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "CUSTOMER_id")
    )
    private Set<Customer> customerSet = new HashSet<>();
	
	public Agent() {}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Customer> getCustomerSet() {
		return customerSet;
	}

	public void setCustomerSet(Set<Customer> customerSet) {
		this.customerSet = customerSet;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Agent [agentId=" + agentId + ", user=" + user + ", agentCode=" + agentCode + ", userId=" + userId
				+ ", isActive=" + isActive + ", customerSet=" + customerSet + "]";
	}
	
}
