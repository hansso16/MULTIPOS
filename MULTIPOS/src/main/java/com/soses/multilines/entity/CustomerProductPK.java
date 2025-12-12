package com.soses.multilines.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CustomerProductPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="CUSTOMER_ID", unique=true, nullable=false)
	private Integer customerId;

	@Column(name="PRODUCT_ID", unique=true, nullable=false)
	private Integer productId;

	public CustomerProductPK() {
	}
	
	@Override
	public String toString() {
		return "CustomerProductPK [customerId=" + customerId + ", productId=" + productId + "]";
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

	@Override
	public int hashCode() {
		return Objects.hash(customerId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerProductPK other = (CustomerProductPK) obj;
		return customerId == other.customerId && productId == other.productId;
	}
	
}
