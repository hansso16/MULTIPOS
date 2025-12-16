package com.soses.multilines.controller.customerinv;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import com.soses.multilines.dto.CustomerInventoryForm;
import com.soses.multilines.dto.CustomerProductFormDTO;
import com.soses.multilines.entity.Agent;
import com.soses.multilines.entity.Customer;
import com.soses.multilines.entity.CustomerInventory;
import com.soses.multilines.entity.Product;
import com.soses.multilines.service.ProductService;
import com.soses.multilines.service.agent.AgentService;
import com.soses.multilines.service.customer.CustomerService;
import com.soses.multilines.service.customerinv.CustomerInventoryService;
import com.soses.multilines.service.user.UserService;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CustomerInventoryController {

private static final Logger log = LoggerFactory.getLogger(CustomerInventoryController.class);
	
	private final CustomerInventoryService inventoryService;
	private final AgentService agentService;
	private final CustomerService customerService;
	private final ProductService productService;
	private final UserService userService;
	
	public CustomerInventoryController(CustomerInventoryService inventoryService, AgentService agentService,
			CustomerService customerService, ProductService productService, UserService userService) {
		super();
		this.inventoryService = inventoryService;
		this.agentService = agentService;
		this.customerService = customerService;
		this.productService = productService;
		this.userService = userService;
	}

	@GetMapping("/customerinventory/{customerId}")
	public String showInventoryForm(@PathVariable Integer customerId, Model model, Principal principal) {
		log.info("ENTER: showInventoryForm(customerId,agentId,model)");
		
		// 1. Validate agent → must exist
		Integer userid = userService.getUserId(principal.getName());
        Agent agent = agentService.findByUserId(userid);
        Customer customer = customerService.findById(customerId);
        LocalDate visitDate = LocalDate.now();
        List<CustomerProductFormDTO> dtoList = null;
        
        // 2. Check if today's record already exists
        List<CustomerInventory> todayInventory = inventoryService
                .getTodayInventory(customerId, agent.getAgentId(), visitDate);
        
        CustomerInventoryForm dto = new CustomerInventoryForm();
        dto.setCustomerId(customerId);
        dto.setAgentId(agent.getAgentId());
        dto.setVisitDate(visitDate);
        dto.setFrequency(customer.getFrequency());

        if (todayInventory.isEmpty()) {
        	//Customer customer = customerService.findById(customerId);
        	
        	List<Product> assignedProducts = productService.getAssignedProducts(customerId);
        	dtoList = inventoryService.getPreviousCycleInventoryMap(customerId, assignedProducts);
        	dto.setItems(dtoList);
        } else {
        	dtoList = inventoryService.convertToReadOnlyDTO(todayInventory, visitDate);
        	dto.setItems(dtoList);
        	dto.setComputed(true);
        	dto.setReadOnly(true);
        }
        
        model.addAttribute("agent", agent);
        model.addAttribute("form", dto);

        return "customerinv/customerinv_input_form";
	}
	
	@PostMapping("/customerinventory/compute")
	public String computeInventoryForm(@ModelAttribute CustomerInventoryForm form, Model model, Principal principal) {
		log.info("ENTER: computeInventoryForm(form,agentId,model)");
		
        form = inventoryService.computeSuggestedOrder(form);
        model.addAttribute("form", form);

        return "customerinv/customerinv_input_form";
	}
	
	@PostMapping("/customerinventory/submit")
	public String submitInventoryForm(@ModelAttribute CustomerInventoryForm form, Model model, Principal principal) {
		log.info("ENTER: submitInventoryForm(customerId,agentId,model)");
		log.info(form.toString());
		
		Integer customerId = form.getCustomerId();
	    Integer agentId = form.getAgentId();
	    LocalDate visitDate = form.getVisitDate();
	    
	    // 1. Save all inventory rows
		inventoryService.saveInventory(form);
		
		// 2. Reload data for display (read only mode)
		Customer customer = customerService.findById(customerId);
		Agent agent = agentService.findById(agentId);
		
		// 3. Reload assigned products
		//List<Product> assignedProducts = productService.getAssignedProducts(customerId);
		
	    // 4. Reload saved records (for read-only display)
		List<CustomerInventory> savedRecords =
	            inventoryService.getInventoryForVisit(customerId, visitDate);
		
		// 5. Convert saved records to DTO for read only rendering
		List<CustomerProductFormDTO> dtoList = inventoryService.convertToReadOnlyDTO(savedRecords, visitDate);
		
		
		CustomerInventoryForm readOnlyForm = new CustomerInventoryForm();
		readOnlyForm.setCustomerId(customerId);
		readOnlyForm.setAgentId(agentId);
		readOnlyForm.setVisitDate(visitDate);
		readOnlyForm.setItems(dtoList);
		readOnlyForm.setFrequency(customer.getFrequency());
		readOnlyForm.setReadOnly(true);
		readOnlyForm.setComputed(true);
		
		model.addAttribute("customer", customer);
	    model.addAttribute("agent", agent);
	    model.addAttribute("form", readOnlyForm);
	    
        return "customerinv/customerinv_input_form";
	}
}
