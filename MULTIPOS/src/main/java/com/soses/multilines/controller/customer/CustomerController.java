package com.soses.multilines.controller.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import com.soses.multilines.api.customer.CustomerSearchRequest;
import com.soses.multilines.controller.BaseSearchController;
import com.soses.multilines.controller.customerinv.CustomerInvCustomerSearchController;
import com.soses.multilines.entity.Customer;
import com.soses.multilines.service.customer.CustomerSearchService;

import jakarta.validation.Valid;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CustomerController extends BaseSearchController {

	private static final Logger log = LoggerFactory.getLogger(CustomerInvCustomerSearchController.class);
	
	private CustomerSearchService customerSearchService;
	
	public CustomerController(CustomerSearchService customerSearchService) {
		super();
		this.customerSearchService = customerSearchService;
	}
	
	@GetMapping("/customer")
	public String searchEntity(@Valid CustomerSearchRequest customerReq, Errors errors, Model model) {
		log.info("ENTER: searchEntity(customerReq,errors,model)");
		Page<Customer> customerPage = null;
		if (customerReq.getCustomerId() == null) customerReq.setCustomerId("");
		String customerId = customerReq.getCustomerId();
		if (customerId != null) {
			customerPage = customerSearchService.searchCustomer(customerReq);
			if (customerPage != null) {
				setPaginationVariables(customerPage, model);
				model.addAttribute("customerId", customerId);
			}
		}
		return "customer/customer_list";
	}
}
