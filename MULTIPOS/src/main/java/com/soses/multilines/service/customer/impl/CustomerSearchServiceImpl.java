package com.soses.multilines.service.customer.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soses.multilines.api.customer.CustomerSearchRequest;
import com.soses.multilines.common.StringUtil;
import com.soses.multilines.entity.Customer;
import com.soses.multilines.repository.CustomerRepository;
import com.soses.multilines.service.customer.CustomerSearchService;

import jakarta.transaction.Transactional;

@Service("CustomerSearchServiceImpl")
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerSearchServiceImpl implements CustomerSearchService {

	private CustomerRepository customerRepo;
	
	public CustomerSearchServiceImpl(CustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
	}


	@Override
	public Page<Customer> searchCustomer(CustomerSearchRequest request) {

		String customerId = request.getCustomerId();
		Page<Customer> customerPage = null;
		
		int pageSize = 15;
		if (!StringUtil.isEmpty(request.getSize())) {
			pageSize = Integer.parseInt(request.getSize());
		}
		int currentPage = 0;
		if (!StringUtil.isEmpty(request.getPage())) {
			currentPage = Integer.parseInt(request.getPage()) - 1;
		}
		Pageable page = PageRequest.of(currentPage, pageSize);
		
		if (!StringUtil.isEmpty(customerId)) {
			customerPage = customerRepo.findByCustomerCodeContainsOrCustomerNameContains(customerId, customerId, page);
		} else {
			customerPage = customerRepo.findAll(page);
		}
		return customerPage;
	}
	
	public Page<Customer> searchCustomerByAgent(CustomerSearchRequest request, Integer agentId) {
		
		String searchText = request.getSearch();
		Page<Customer> customerPage = null;
		
		int pageSize = 15;
		if (!StringUtil.isEmpty(request.getSize())) {
			pageSize = Integer.parseInt(request.getSize());
		}
		int currentPage = 0;
		if (!StringUtil.isEmpty(request.getPage())) {
			currentPage = Integer.parseInt(request.getPage()) - 1;
		}
		Pageable page = PageRequest.of(currentPage, pageSize);
		
		customerPage = customerRepo.findAssignedForAgent(agentId, searchText, page);
		return customerPage;
	}

}
