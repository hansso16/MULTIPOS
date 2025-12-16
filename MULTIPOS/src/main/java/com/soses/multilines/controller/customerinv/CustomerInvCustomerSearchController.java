package com.soses.multilines.controller.customerinv;

import java.security.Principal;

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
import com.soses.multilines.entity.Agent;
import com.soses.multilines.entity.Customer;
import com.soses.multilines.service.agent.AgentService;
import com.soses.multilines.service.customer.CustomerSearchService;
import com.soses.multilines.service.user.UserService;

import jakarta.validation.Valid;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CustomerInvCustomerSearchController extends BaseSearchController {

	private static final Logger log = LoggerFactory.getLogger(CustomerInvCustomerSearchController.class);
	
	private CustomerSearchService customerSearchService;
	
	private final UserService userService;
	
	private final AgentService agentService;
	
	public CustomerInvCustomerSearchController(CustomerSearchService customerSearchService, UserService userService, AgentService agentService) {
		super();
		this.userService = userService;
		this.customerSearchService = customerSearchService;
		this.agentService = agentService;
	}


	@GetMapping("/customerinventory")
	public String searchEntity(@Valid CustomerSearchRequest customerReq, Errors errors, Model model, Principal principal) {
		log.info("ENTER: searchEntity(customerReq,errors,model)");
		Integer userid = userService.getUserId(principal.getName());
        Agent agent = agentService.findByUserId(userid);
		Page<Customer> customerPage = null;
		customerReq.setSearch(customerReq.getSearch() == null? "" : customerReq.getSearch());
		if (customerReq != null) {
			customerPage = customerSearchService.searchCustomerByAgent(customerReq, agent.getAgentId());
			if (customerPage != null) {
				setPaginationVariables(customerPage, model);
				model.addAttribute("search", customerReq.getSearch());
			}
		}
		return "customerinv/customer_list";
	}
}
