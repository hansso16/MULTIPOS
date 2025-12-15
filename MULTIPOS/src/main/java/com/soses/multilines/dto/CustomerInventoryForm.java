package com.soses.multilines.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerInventoryForm {
    
    private Integer customerId;
    
    private Integer agentId;
    
    private LocalDate visitDate;
    
    private Integer count;
    
    private Integer frequency;
    
    private boolean readOnly = false;
    
    private boolean computed = false;

    private List<CustomerProductFormDTO> items;
    
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public boolean isComputed() {
		return computed;
	}

	public void setComputed(boolean computed) {
		this.computed = computed;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public List<CustomerProductFormDTO> getItems() {
		return items;
	}

	public void setItems(List<CustomerProductFormDTO> items) {
		this.items = items;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	@Override
	public String toString() {
		return "CustomerInventoryForm [customerId=" + customerId + ", agentId=" + agentId + ", visitDate=" + visitDate
				+ ", count=" + count + ", frequency=" + frequency + ", readOnly=" + readOnly + ", computed=" + computed
				+ ", items=" + items + "]";
	}
}
