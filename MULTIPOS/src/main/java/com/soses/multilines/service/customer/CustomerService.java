package com.soses.multilines.service.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.soses.multilines.api.customer.BaseCustomerResponse;
import com.soses.multilines.api.customer.CustomerProductResponse;
import com.soses.multilines.api.customer.CustomerResponse;
import com.soses.multilines.common.GeneralUtil;
import com.soses.multilines.dto.ProductDTO;
import com.soses.multilines.entity.Customer;
import com.soses.multilines.entity.Product;
import com.soses.multilines.repository.CustomerProductRepository;
import com.soses.multilines.repository.CustomerRepository;
import com.soses.multilines.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	private CustomerRepository customerRepo;
	
	private ProductRepository productRepo;
	
	private CustomerProductRepository customerProductRepo;
	
	public CustomerService(CustomerRepository customerRepo, ProductRepository productRepo, CustomerProductRepository customerProductRepo) {
		super();
		this.customerRepo = customerRepo;
		this.productRepo = productRepo;
		this.customerProductRepo = customerProductRepo;
	}
	
	public BaseCustomerResponse initCustomerDetails() {
		log.info("ENTER: initCustomerDetails()");
		CustomerResponse response = new CustomerResponse();
		List<Customer> customerList = customerRepo.findAll();
		
		if (!GeneralUtil.isListEmpty(customerList)) {
			response.setCustomerList(customerList);
		}
		
		return response;
	}
	
	@PreAuthorize("hasAuthority('VIEW_AC_ASSIGN')")
	public CustomerProductResponse getProductsForCustomer(String customerId) {
		
		log.info("ENTER: getProductsForCustomer(customerId)");
		Optional<Customer> c = customerRepo.findById(Integer.valueOf(customerId));
		if (c == null) {
			//error
		}
		Customer customer = c.get();
		
		List<Product> assigned = new ArrayList<>(customer.getProductSet());
		List<ProductDTO> assignedDTO = assigned.stream().map(p -> new ProductDTO(p.getProductId(), p.getProductCode()
				, p.getProductDescription()))
				.toList();
		
		List<Product> available = productRepo.findProductsNotAssignedToCustomer(customerId);
		List<ProductDTO> availableDTO = available.stream().map(p -> new ProductDTO(p.getProductId(), p.getProductCode()
				, p.getProductDescription()))
				.toList();
		
		CustomerProductResponse res = new CustomerProductResponse(assignedDTO, availableDTO);
		return res;
	}
	
	@PreAuthorize("hasAuthority('SAVE_AC_ASSIGN')")
	public void updateCustomerProducts(Integer customerId, List<Integer> productIdList) {
		
		customerProductRepo.deleteByCustomerId(customerId);
		
		for (Integer productId : productIdList) {
			customerProductRepo.insert(customerId, productId);
		}
	}
	
	public Customer findById(Integer id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}
