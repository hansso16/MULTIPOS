package com.soses.multilines.service.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.soses.multilines.api.agent.AgentCustomerResponse;
import com.soses.multilines.api.agent.AgentResponse;
import com.soses.multilines.api.agent.BaseAgentResponse;
import com.soses.multilines.common.GeneralUtil;
import com.soses.multilines.dto.CustomerDTO;
import com.soses.multilines.entity.Agent;
import com.soses.multilines.entity.Customer;
import com.soses.multilines.repository.AgentCustomerRepository;
import com.soses.multilines.repository.AgentRepository;
import com.soses.multilines.repository.CustomerRepository;
import com.soses.multilines.service.customer.CustomerService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AgentService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	private AgentRepository agentRepo;
	
	private CustomerRepository customerRepo;
	
	private AgentCustomerRepository agentCustomerRepo;
	
	public AgentService(AgentRepository agentRepo, CustomerRepository customerRepo, AgentCustomerRepository agentCustomerRepo) {
		super();
		this.agentRepo = agentRepo;
		this.customerRepo = customerRepo;
		this.agentCustomerRepo = agentCustomerRepo;
	}
	
	public BaseAgentResponse initAgentDetails() {
		log.info("ENTER: initCustomerDetails()");
		AgentResponse response = new AgentResponse();
		List<Agent> agentList = agentRepo.findAll();
		
		if (!GeneralUtil.isListEmpty(agentList)) {
			response.setAgentList(agentList);
		}
		
		return response;
	}
	
	@PreAuthorize("hasAnyAuthority('VIEW_AC_ASSIGN')")
	public AgentCustomerResponse getCustomerForAgent(String agentId) {
		
		log.info("ENTER: getCustomerForAgent(agentId)");
		Optional<Agent> a = agentRepo.findById(Integer.valueOf(agentId));
		if (a == null) {
			//error
		}
		Agent agent= a.get();
		
		List<Customer> assigned = new ArrayList<>(agent.getCustomerSet());
		List<CustomerDTO> assignedDTO = assigned.stream().map(p -> new CustomerDTO(p.getCustomerId(), p.getCustomerCode()
				, p.getCustomerName()))
				.toList();
		
		List<Customer> available = customerRepo.findAvailableForAgent(agentId);
		List<CustomerDTO> availableDTO = available.stream().map(p -> new CustomerDTO(p.getCustomerId(), p.getCustomerCode()
				, p.getCustomerName()))
				.toList();
		
		AgentCustomerResponse res = new AgentCustomerResponse(assignedDTO, availableDTO);
		return res;
	}
	
	@PreAuthorize("hasAuthority('SAVE_AC_ASSIGN')")
	public void updateAgentCustomers(Integer agentId, List<Integer> customerIdList) {
		
		agentCustomerRepo.deleteByAgentId(agentId);
		
		for (Integer customerId : customerIdList) {
			agentCustomerRepo.insert(agentId, customerId);
		}
	}
	
	public Agent findById(Integer id) {
        return agentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
    }
	
    public List<Agent> findAll() {
        return agentRepo.findAll();
    }

    public Agent findByUserId(Integer userId) {
        return agentRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Agent not found for user"));
    }

    public List<Customer> getCustomersForAgent(Integer agentId) {
        return agentCustomerRepo.findAssignedCustomers(agentId);
    }
}
