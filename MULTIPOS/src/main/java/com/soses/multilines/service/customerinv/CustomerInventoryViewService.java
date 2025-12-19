package com.soses.multilines.service.customerinv;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.soses.multilines.api.customerinv.CustomerInventoryVisitRequest;
import com.soses.multilines.common.StringUtil;
import com.soses.multilines.dto.CustomerInventoryVisitDTO;
import com.soses.multilines.entity.CustomerInventory;
import com.soses.multilines.repository.CustomerInventoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerInventoryViewService {

	//private static final Logger log = LoggerFactory.getLogger(CustomerInventoryViewService.class);
	
	private final CustomerInventoryRepository inventoryRepo;
	
	public CustomerInventoryViewService(CustomerInventoryRepository inventoryRepo) {
		super();
		this.inventoryRepo = inventoryRepo;
	}

	/**
     * LIST PAGE
     * Back office: list inventory visits
     */
    public Page<CustomerInventoryVisitDTO> getInventoryVisits(CustomerInventoryVisitRequest request) {

    	String search = request.getSearch();
		Page<CustomerInventoryVisitDTO> dtoPage = null;

    	int pageSize = 5;
		if (!StringUtil.isEmpty(request.getSize())) {
			pageSize = Integer.parseInt(request.getSize());
		}
		int currentPage = 0;
		if (!StringUtil.isEmpty(request.getPage())) {
			currentPage = Integer.parseInt(request.getPage()) - 1;
		}
		Pageable page = PageRequest.of(currentPage, pageSize);
    	
		if (!StringUtil.isEmpty(search)) {
			dtoPage = inventoryRepo.findDistinctVisitsByCustomerCodeOrAgentCode(search, search, page);
		} else {
			dtoPage = inventoryRepo.findDistinctVisits(page);			
		}
    	
        return dtoPage;
    }

    /**
     * DETAIL PAGE
     * View inventory per visit
     */
    public List<CustomerInventory> getInventoryDetails(
            Integer customerId,
            Integer agentId,
            LocalDate visitDate) {

        return inventoryRepo.findByIdCustomerIdAndIdAgentIdAndIdVisitDate(
                customerId, agentId, visitDate);
    }

    /**
     * HARD DELETE – entire visit
     */
    @Transactional
    @PreAuthorize("hasAuthority('DELETE_CI_DETAIL')")
    public void deleteInventoryVisit(
            Integer customerId,
            Integer agentId,
            LocalDate visitDate) {

        int deleted = inventoryRepo.deleteByIdCustomerIdAndIdAgentIdAndIdVisitDate(
                customerId, agentId, visitDate);
        
        if (deleted == 0) {
        	throw new IllegalStateException(
                    "No inventory records found for deletion"
                );
        }
    }
}
