package com.soses.multilines.controller.agent;

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

import com.soses.multilines.api.agent.AgentCustomerResponse;
import com.soses.multilines.api.agent.AgentResponse;
import com.soses.multilines.service.agent.AgentService;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AgentCustomerAssignmentController {

private static final Logger log = LoggerFactory.getLogger(AgentCustomerAssignmentController.class);
	
	private AgentService agentService;
	
	public AgentCustomerAssignmentController(AgentService agentService) {
		super();
		this.agentService = agentService;
	}
	
	@GetMapping("/agent/customer")
	public String loadAgentCustomer(Model model) {
		log.info("ENTER: loadAgentCustomer(model)");
		
		AgentResponse res = (AgentResponse) agentService.initAgentDetails();
		model.addAttribute("res", res);
		return "agent/agent_customer_assign";
	}
	
	@GetMapping("/api/agent/{agentId}/customer")
	@ResponseBody
	public AgentCustomerResponse getAgentCustomerDetails(@PathVariable String agentId) {
		log.info("ENTER: getAgentCustomerDetails(agentId,model)");
		
		AgentCustomerResponse res = agentService.getCustomerForAgent(agentId);
		return res;
	}
	
	@PostMapping("/api/agent/{agentId}/customer")
	@ResponseBody
	public ResponseEntity<Void> saveAgentCustomer(@PathVariable Integer agentId, @RequestBody List<Integer> productIdList) {

		log.info("saveAgentCustomer(agentId,productIdList)");
		agentService.updateAgentCustomers(agentId, productIdList);
		return ResponseEntity.ok().build();
	}
}
