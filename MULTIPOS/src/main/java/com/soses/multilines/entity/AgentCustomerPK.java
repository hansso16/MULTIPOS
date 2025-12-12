package com.soses.multilines.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AgentCustomerPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="AGENT_ID", unique=true, nullable=false)
	private Integer agentId;

	@Column(name="CUSTOMER_ID", unique=true, nullable=false)
	private Integer customerId;

	public AgentCustomerPK() {
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "AgentCustomerPK [agentId=" + agentId + ", customerId=" + customerId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agentId, customerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgentCustomerPK other = (AgentCustomerPK) obj;
		return Objects.equals(agentId, other.agentId) && Objects.equals(customerId, other.customerId);
	}
	
}
