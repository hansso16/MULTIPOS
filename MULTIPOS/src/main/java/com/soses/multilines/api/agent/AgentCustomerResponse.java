package com.soses.multilines.api.agent;

import java.util.List;

import com.soses.multilines.dto.CustomerDTO;

public class AgentCustomerResponse {

	private List<CustomerDTO> assignedProducts;
	
	private List<CustomerDTO> availableProducts;
	
	public AgentCustomerResponse() {}

	public AgentCustomerResponse(List<CustomerDTO> assignedProducts, List<CustomerDTO> availableProducts) {
		super();
		this.assignedProducts = assignedProducts;
		this.availableProducts = availableProducts;
	}

	public List<CustomerDTO> getAssignedProducts() {
		return assignedProducts;
	}

	public void setAssignedProducts(List<CustomerDTO> assignedProducts) {
		this.assignedProducts = assignedProducts;
	}

	public List<CustomerDTO> getAvailableProducts() {
		return availableProducts;
	}

	public void setAvailableProducts(List<CustomerDTO> availableProducts) {
		this.availableProducts = availableProducts;
	}

	@Override
	public String toString() {
		return "AgentCustomerResponse [assignedProducts=" + assignedProducts + ", availableProducts="
				+ availableProducts + "]";
	}
	
}
