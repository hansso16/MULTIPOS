package com.soses.multilines.service.customer;

import org.springframework.data.domain.Page;

import com.soses.multilines.api.customer.CustomerSearchRequest;
import com.soses.multilines.entity.Customer;

public interface CustomerSearchService {

	Page<Customer> searchCustomer(CustomerSearchRequest request);
	
	Page<Customer> searchCustomerByAgent(CustomerSearchRequest request, Integer agentId);
}
