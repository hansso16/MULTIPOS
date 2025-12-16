package com.soses.multilines.controller.customerinv;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soses.multilines.api.customerinv.CustomerInventoryVisitRequest;
import com.soses.multilines.controller.BaseSearchController;
import com.soses.multilines.dto.CustomerInventoryForm;
import com.soses.multilines.dto.CustomerInventoryVisitDTO;
import com.soses.multilines.dto.CustomerProductFormDTO;
import com.soses.multilines.entity.Agent;
import com.soses.multilines.entity.Customer;
import com.soses.multilines.entity.CustomerInventory;
import com.soses.multilines.service.agent.AgentService;
import com.soses.multilines.service.customer.CustomerService;
import com.soses.multilines.service.customerinv.CustomerInventoryService;
import com.soses.multilines.service.customerinv.CustomerInventoryViewService;


@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CustomerInventoryViewController extends BaseSearchController {

	private static final Logger log = LoggerFactory.getLogger(CustomerInventoryViewController.class);
	
	private final CustomerInventoryViewService ciViewService;
	
    private final CustomerInventoryService ciService;
    
    private final CustomerService customerService;
    
    private final AgentService agentService;

	public CustomerInventoryViewController(CustomerInventoryViewService ciViewService,
			CustomerInventoryService ciService, CustomerService customerService, AgentService agentService) {
		super();
		this.ciViewService = ciViewService;
		this.ciService = ciService;
		this.customerService = customerService;
		this.agentService = agentService;
	}

	@GetMapping("/customer/inventory")
    public String viewVisit(CustomerInventoryVisitRequest request, Model model) {
		log.info("ENTER: viewInventory(request,model)");
		Page<CustomerInventoryVisitDTO> ciPage = ciViewService.getInventoryVisits(request);
		setPaginationVariables(ciPage, model);
		model.addAttribute("search", request.getSearch());

        return "customerinv/customer_inventory_visit";
    }
	
	@GetMapping("/customer/inventory/{customerId}/{agentId}/{visitDate}")
	public String viewVisitDetails(@PathVariable Integer customerId, 
			@PathVariable Integer agentId, 
			@PathVariable LocalDate visitDate,
			Model model) {
		
		log.info("ENTER: viewVisitDetails(customerId,agentId,model)");
		
        List<CustomerProductFormDTO> dtoList = null;
        Customer c = customerService.findById(customerId);
        Agent a = agentService.findById(agentId);
        
        List<CustomerInventory> todayInventory = ciService
                .getTodayInventory(customerId, agentId, visitDate);
        
        CustomerInventoryForm dto = new CustomerInventoryForm();
        dto.setCustomerId(customerId);
        dto.setAgentId(agentId);
        dto.setVisitDate(visitDate);
        dto.setFrequency(c.getFrequency());

        dtoList = ciService.convertToReadOnlyDTO(todayInventory, visitDate);
        dto.setItems(dtoList);
        dto.setReadOnly(true);
        
        model.addAttribute("customer", c);
        model.addAttribute("agent", a);
        model.addAttribute("form", dto);
        
		return "customerinv/customer_inventory_detail";
	}
	
	@PostMapping("/customer/inventory/visit/delete")
	public String deleteInventoryVisit(
	        @RequestParam Integer customerId,
	        @RequestParam Integer agentId,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	                LocalDate visitDate,
	        RedirectAttributes redirectAttributes
	) {
	    try {
	    	ciViewService.deleteInventoryVisit(customerId, agentId, visitDate);
	        redirectAttributes.addFlashAttribute(
	            "successMessage",
	            "Inventory visit deleted successfully."
	        );
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute(
	            "errorMessage",
	            "Failed to delete inventory visit."
	        );
	    }

	    return "redirect:/customer/inventory";
	}
	
    
}
