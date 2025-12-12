package com.soses.multilines.api.customer;

import java.util.List;

import com.soses.multilines.dto.ProductDTO;

public class CustomerProductResponse extends BaseCustomerResponse {

	private List<ProductDTO> assignedProducts;
	
	private List<ProductDTO> availableProducts;

	public CustomerProductResponse() {}
	
	public CustomerProductResponse(List<ProductDTO> assignedProducts, List<ProductDTO> availableProducts) {
		super();
		this.assignedProducts = assignedProducts;
		this.availableProducts = availableProducts;
	}

	public List<ProductDTO> getAssignedProducts() {
		return assignedProducts;
	}

	public void setAssignedProducts(List<ProductDTO> assignedProducts) {
		this.assignedProducts = assignedProducts;
	}

	public List<ProductDTO> getAvailableProducts() {
		return availableProducts;
	}

	public void setAvailableProducts(List<ProductDTO> availableProducts) {
		this.availableProducts = availableProducts;
	}

	@Override
	public String toString() {
		return "CustomerProductResponse [assignedProducts=" + assignedProducts + ", availableProducts="
				+ availableProducts + "]";
	}
	
	
}
