package com.soses.multilines.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="agent_customer")
@NamedQuery(name="AgentCustomer.findAll", query="SELECT ac FROM AgentCustomer ac")
public class AgentCustomer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 721598590438193022L;

	@EmbeddedId
	private AgentCustomerPK id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("agentId")
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;

	public AgentCustomer() {}

	public AgentCustomerPK getId() {
		return id;
	}

	public void setId(AgentCustomerPK id) {
		this.id = id;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
