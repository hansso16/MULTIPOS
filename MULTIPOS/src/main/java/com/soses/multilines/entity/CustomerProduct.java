package com.soses.multilines.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_product")
@NamedQuery(name="CustomerProduct.findAll", query="SELECT r FROM CustomerProduct r")
public class CustomerProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CustomerProductPK id;
	
	@ManyToOne
	@MapsId("customerId")
	@JoinColumn(name="customer_id")
	private Customer customer;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name="product_id")
	private Product product;

	public CustomerProduct() {}
	
	public CustomerProductPK getId() {
		return id;
	}

	public void setId(CustomerProductPK id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
