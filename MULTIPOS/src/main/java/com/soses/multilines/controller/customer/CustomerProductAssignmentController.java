package com.soses.multilines.controller.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.soses.multilines.api.customer.CustomerProductResponse;
import com.soses.multilines.api.customer.CustomerResponse;
import com.soses.multilines.service.customer.CustomerService;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CustomerProductAssignmentController {

	private static final Logger log = LoggerFactory.getLogger(CustomerProductAssignmentController.class);
	
	private CustomerService customerService;
	
	public CustomerProductAssignmentController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping("/customer/product")
	public String addCustomerInventory(Model model) {
		log.info("ENTER: addCustomerInventory(model)");
		
		CustomerResponse res = (CustomerResponse) customerService.initCustomerDetails();
		model.addAttribute("res", res);
		return "customer/customer_product_assign";
	}
	
	@GetMapping("/api/customer/{customerId}/product")
	@ResponseBody
	public CustomerProductResponse getCustomerInventoryDetails(@PathVariable String customerId) {
		log.info("ENTER: getCustomerInventoryDetails(customerId,model)");
		
		CustomerProductResponse res = customerService.getProductsForCustomer(customerId);
		return res;
	}
	
	@PostMapping("/api/customer/{customerId}/product")
	@ResponseBody
	public ResponseEntity<Void> saveCustomerProducts(@PathVariable Integer customerId, @RequestBody List<Integer> productIdList) {

		log.info("saveCustomerProducts(customerId,productIdList)");
		customerService.updateCustomerProducts(customerId, productIdList);
		return ResponseEntity.ok().build();
	}
}
