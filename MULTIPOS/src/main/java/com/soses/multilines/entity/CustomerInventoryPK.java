package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CustomerInventoryPK implements Serializable {

	private static final long serialVersionUID = -8319003287174070303L;

	@Column(name = "agent_id", nullable = false)
    private Integer agentId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;
    
    public CustomerInventoryPK() {}

	public CustomerInventoryPK(Integer agentId, Integer customerId, Integer productId, LocalDate visitDate) {
		super();
		this.agentId = agentId;
		this.customerId = customerId;
		this.productId = productId;
		this.visitDate = visitDate;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	@Override
	public String toString() {
		return "CustomerInventoryPK [agentId=" + agentId + ", customerId=" + customerId + ", productId=" + productId
				+ ", visitDate=" + visitDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agentId, customerId, productId, visitDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInventoryPK other = (CustomerInventoryPK) obj;
		return Objects.equals(agentId, other.agentId) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(productId, other.productId) && Objects.equals(visitDate, other.visitDate);
	}
	
}
