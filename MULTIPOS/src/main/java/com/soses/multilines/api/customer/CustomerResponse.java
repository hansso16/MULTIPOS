package com.soses.multilines.api.customer;

import java.util.List;

import com.soses.multilines.entity.Customer;

public class CustomerResponse extends BaseCustomerResponse {

	List<Customer> customerList;

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	@Override
	public String toString() {
		return "CustomerResponse [customerList=" + customerList + "]";
	}
	
}
