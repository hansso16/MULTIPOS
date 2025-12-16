package com.soses.multilines.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRODUCT_ID", unique=true, nullable=false)
	private int productId;

	@Column(name="PRODUCT_DESCRIPTION", length=50)
	private String productDescription;

	@Column(name="PRODUCT_CODE", nullable=false, length=10)
	private String productCode;
	
	@Column(name="count")
	private Integer count;
	
	@ManyToMany(mappedBy = "productSet")
    private Set<Customer> customerSet;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Set<Customer> getCustomerSet() {
		return customerSet;
	}

	public void setCustomerSet(Set<Customer> customerSet) {
		this.customerSet = customerSet;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productDescription=" + productDescription + ", productCode="
				+ productCode + ", count=" + count + ", customerSet=" + customerSet + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(productCode, productDescription, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productCode, other.productCode)
				&& Objects.equals(productDescription, other.productDescription) && productId == other.productId;
	}

}
