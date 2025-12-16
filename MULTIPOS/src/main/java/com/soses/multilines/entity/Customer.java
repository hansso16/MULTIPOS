package com.soses.multilines.entity;

import java.io.Serializable;
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


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMER_ID", unique=true, nullable=false)
	private int customerId;

	@Column(name="customer_NAME", length=50)
	private String customerName;

	@Column(name="CUSTOMER_CODE", nullable=false, length=10)
	private String customerCode;
	
	@Column(name="FREQUENCY")
	private Integer frequency = 14;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "customer_product",
        joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCT_id")
    )
    private Set<Product> productSet = new HashSet<>();
	
	@ManyToMany(mappedBy = "customerSet")
    private Set<Agent> agentSet;

	public Customer() {
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerCode="
				+ customerCode + ", frequency=" + frequency + ", productSet=" + productSet + ", agentSet=" + agentSet
				+ "]";
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Set<Product> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}

	public Set<Agent> getAgentSet() {
		return agentSet;
	}

	public void setAgentSet(Set<Agent> agentSet) {
		this.agentSet = agentSet;
	}
	

}