package com.soses.multilines.service.customerinv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.soses.multilines.common.GeneralUtil;
import com.soses.multilines.dto.CustomerInventoryForm;
import com.soses.multilines.dto.CustomerProductFormDTO;
import com.soses.multilines.entity.CustomerInventory;
import com.soses.multilines.entity.CustomerInventoryPK;
import com.soses.multilines.entity.Product;
import com.soses.multilines.repository.CustomerInventoryRepository;
import com.soses.multilines.service.ProductService;
import com.soses.multilines.service.agent.AgentService;
import com.soses.multilines.service.customer.CustomerService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerInventoryService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	private final CustomerInventoryRepository inventoryRepo;
	
    private final AgentService agentService;
    
    private final CustomerService customerService;
    
    private final ProductService productService;

	public CustomerInventoryService(CustomerInventoryRepository inventoryRepo, AgentService agentService,
			CustomerService customerService, ProductService productService) {
		super();
		this.inventoryRepo = inventoryRepo;
		this.agentService = agentService;
		this.customerService = customerService;
		this.productService = productService;
	}
    
	public void saveInventory(CustomerInventoryForm form) {

		log.info("saveInventory(CustomerInventoryForm)");
		
		Integer customerId = form.getCustomerId();
		Integer agentId = form.getAgentId();
		LocalDate visitDate = form.getVisitDate();
		
        // -----------------------------------
        // 1. VALIDATIONS
        // -----------------------------------
//        if (beginningQty < 0 || endingQty < 0 || deliveryQty < 0) {
//            throw new IllegalArgumentException("Quantities cannot be negative.");
//        }

        // Check if entry already exists → prevent duplicate inventory
        boolean exists = inventoryRepo
                .existsByIdCustomerIdAndIdVisitDate(customerId, visitDate);

        if (exists) {
            throw new IllegalStateException(
                    "Inventory already exists for this customer, and visit date."
            );
        }

        for (CustomerProductFormDTO item : form.getItems()) {
        	
        	CustomerInventory entity = new CustomerInventory();

            // composite key
            CustomerInventoryPK id = new CustomerInventoryPK();
            id.setCustomerId(customerId);
            id.setProductId(item.getProductId());
            id.setVisitDate(visitDate);

            entity.setId(id);

            // relations
            entity.setAgent(agentService.findById(agentId));
            entity.setCustomer(customerService.findById(customerId));
            entity.setProduct(productService.findById(item.getProductId()));

            // inventory fields
            entity.setBeginningCase(GeneralUtil.nvl(item.getBeginningCase()));
            entity.setBeginningPiece(GeneralUtil.nvl(item.getBeginningPiece()));

            entity.setDeliveryCase(GeneralUtil.nvl(item.getDeliveryCase()));
            entity.setDeliveryPiece(GeneralUtil.nvl(item.getDeliveryPiece()));
            entity.setDeliveryDate(item.getDeliveryDate());

            entity.setEndingCase(GeneralUtil.nvl(item.getEndingCase()));
            entity.setEndingPiece(GeneralUtil.nvl(item.getEndingPiece()));
            
            entity.setFinalCase(GeneralUtil.nvl(item.getFinalCase()));
            entity.setFinalPiece(GeneralUtil.nvl(item.getFinalPiece()));

            // metadata
            entity.setEntryTimestamp(LocalDateTime.now());
            entity.setSubmittedBy(customerId.toString());

            inventoryRepo.save(entity);
        }
    }
	
	/**
     * Get inventory for a customer for a given visit date.
     */
    public List<CustomerInventory> getInventoryForVisit(Integer customerId, LocalDate visitDate) {
        return inventoryRepo.findByCustomerAndVisitDate(customerId, visitDate);
    }
    
    /**
     * Get previous inventory for auto-fill logic.
     */
    public Optional<CustomerInventory> getPreviousInventory(Integer customerId, Integer productId) {
        List<CustomerInventory> list = inventoryRepo.findPreviousInventory(customerId, productId);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
    
    public List<CustomerProductFormDTO> getPreviousCycleInventoryMap(Integer customerId, List<Product> assignedProducts) {
    	
    	List<CustomerProductFormDTO> list = new ArrayList<>();
    	
    	for (Product p : assignedProducts) {
    		Optional<CustomerInventory> prevOpt = getPreviousInventory(customerId, p.getProductId());
    		CustomerProductFormDTO dto = new CustomerProductFormDTO();
    		dto.setProductId(p.getProductId());
    		dto.setProductCode(p.getProductCode());
    		dto.setProductName(p.getProductDescription());
    		prevOpt.ifPresent(prev -> { 
    			 dto.setBeginningCase(prev.getEndingCase());
    			 dto.setBeginningPiece(prev.getEndingPiece());
    			 dto.setBeginningLocked(true);
    		});
    		list.add(dto);
    	}
    	
    	return list;
    }
    
    /**
     * Delete all inventory entries for a given (customer, visitDate).
     * Usually admin-only.
     */
    @Transactional
    public void deleteInventoryVisit(Integer customerId, LocalDate visitDate) {
        //inventoryRepo.deleteByIdCustomerIdAndIdVisitDate(customerId, visitDate);
    }
    
    public List<CustomerProductFormDTO> convertToReadOnlyDTO(List<CustomerInventory> list, LocalDate visitDate) {

        List<CustomerProductFormDTO> dtos = new ArrayList<>();

        for (CustomerInventory ci : list) {

            CustomerProductFormDTO dto = new CustomerProductFormDTO();
            dto.setProductId(ci.getProduct().getProductId());
            dto.setProductCode(ci.getProduct().getProductCode());
            dto.setProductName(ci.getProduct().getProductDescription());

            dto.setBeginningCase(ci.getBeginningCase());
            dto.setBeginningPiece(ci.getBeginningPiece());
            dto.setBeginningLocked(true);

            dto.setDeliveryCase(ci.getDeliveryCase());
            dto.setDeliveryPiece(ci.getDeliveryPiece());
            dto.setDeliveryDate(ci.getDeliveryDate());

            dto.setEndingCase(ci.getEndingCase());
            dto.setEndingPiece(ci.getEndingPiece());
            
            dto.setFinalCase(ci.getFinalCase());
            dto.setFinalPiece(ci.getFinalPiece());
            
            computeSuggestedOrder(ci, dto, visitDate);

            // flag for read-only UI
            dto.setReadOnly(true);

            dtos.add(dto);
        }

        return dtos;
    }
    
    public List<CustomerInventory> getTodayInventory(Integer customerId, Integer agentId, LocalDate visitDate) {
        return inventoryRepo.findByCustomerAgentVisitDate(customerId, agentId, visitDate);
    }
    
    private void computeSuggestedOrder(CustomerInventory ci, CustomerProductFormDTO dto, LocalDate  visitDate) {
    	
    	int beginningCase = ci.getBeginningCase() == null? 0 : ci.getBeginningCase();
    	int beginningPiece = ci.getBeginningPiece() == null? 0 : ci.getBeginningPiece();
    	
    	int deliveryCase = ci.getDeliveryCase() == null? 0 : ci.getDeliveryCase();
    	int deliveryPiece = ci.getDeliveryPiece() == null? 0 : ci.getDeliveryPiece();
    	
    	int endingCase = ci.getEndingCase() == null? 0 : ci.getEndingCase();
    	int endingPiece = ci.getEndingPiece() == null? 0 : ci.getEndingPiece();
    	
    	//Integer count = ci.getProduct().getCount();
    	//Integer frequency = ci.getCustomer().getFrequency();
    	int count = 1;
    	int frequency = 14;
    	int productFrequency = (int) ChronoUnit.DAYS.between(ci.getDeliveryDate(), visitDate);
    	int requiredDays = productFrequency + (frequency/2);

        int beginQty = (beginningCase * count) + beginningPiece;
        int endQty   = (endingCase * count) + endingPiece;
        int deliveryQty   = (deliveryCase * count) + deliveryPiece;

        Integer consumed = beginQty + deliveryQty - endQty;
        if (consumed < 0) consumed = 0;
        
        double du = Double.valueOf(consumed)/Double.valueOf(frequency);
        Integer dailyUsage = (int) Math.ceil(du);
        
        Integer requiredQty = dailyUsage * requiredDays;
        Integer suggestedPieces = requiredQty - endQty;
        if (suggestedPieces < 0) suggestedPieces = 0;
        
        //convert back to cases
        Integer suggestedCase = suggestedPieces / count;
        Integer suggestedPiece = suggestedPieces % count;
        
        dto.setSuggestedCase(suggestedCase);
        dto.setSuggestedPiece(suggestedPiece);
    }
    
    private void computeSuggestedOrder(CustomerProductFormDTO ci, LocalDate visitDate) {
    	int beginningCase = ci.getBeginningCase() == null? 0 : ci.getBeginningCase();
    	int beginningPiece = ci.getBeginningPiece() == null? 0 : ci.getBeginningPiece();
    	
    	int deliveryCase = ci.getDeliveryCase() == null? 0 : ci.getDeliveryCase();
    	int deliveryPiece = ci.getDeliveryPiece() == null? 0 : ci.getDeliveryPiece();
    	
    	int endingCase = ci.getEndingCase() == null? 0 : ci.getEndingCase();
    	int endingPiece = ci.getEndingPiece() == null? 0 : ci.getEndingPiece();
    	
    	//Integer count = ci.getProduct().getCount();
    	//Integer frequency = ci.getCustomer().getFrequency();
    	int count = 1;
    	int frequency = 14;
    	int productFrequency = (int) ChronoUnit.DAYS.between(ci.getDeliveryDate(), visitDate);
    	int requiredDays = productFrequency + (frequency/2);

        int beginQty = (beginningCase * count) + beginningPiece;
        int endQty   = (endingCase * count) + endingPiece;
        int deliveryQty   = (deliveryCase * count) + deliveryPiece;

        int consumed = beginQty + deliveryQty - endQty;
        if (consumed < 0) consumed = 0;
        
        double du = Double.valueOf(consumed)/Double.valueOf(frequency);
        Integer dailyUsage = (int) Math.ceil(du);
        
        Integer requiredQty = dailyUsage * requiredDays;
        Integer suggestedPieces = requiredQty - endQty;
        if (suggestedPieces < 0) suggestedPieces = 0;
        
        //convert back to cases
        Integer suggestedCase = suggestedPieces / count;
        Integer suggestedPiece = suggestedPieces % count;
        
        ci.setSuggestedCase(suggestedCase);
        ci.setSuggestedPiece(suggestedPiece);
    }
    
    public CustomerInventoryForm computeSuggestedOrder(CustomerInventoryForm form) {
    	
    	List<CustomerProductFormDTO> list = form.getItems();
    	form.setComputed(true);
    	for (CustomerProductFormDTO dto : list) {
            computeSuggestedOrder(dto, form.getVisitDate());
            // flag for read-only UI
            dto.setReadOnly(true);
            dto.setBeginningLocked(true);
        }
    	
    	return form;
    }
}
