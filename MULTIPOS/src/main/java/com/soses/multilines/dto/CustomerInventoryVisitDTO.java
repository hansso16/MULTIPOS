package com.soses.multilines.dto;

import java.time.LocalDate;

public class CustomerInventoryVisitDTO {

	private Integer customerId;
	private String customerCode;
    private String customerName;

    private Integer agentId;
    private String agentCode;

    private LocalDate visitDate;
    private Long productCount;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public LocalDate getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}
	public Long getProductCount() {
		return productCount;
	}
	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}
	@Override
	public String toString() {
		return "CustomerInventoryVisitDTO [customerId=" + customerId + ", customerCode=" + customerCode
				+ ", customerName=" + customerName + ", agentId=" + agentId + ", agentCode=" + agentCode
				+ ", visitDate=" + visitDate + ", productCount=" + productCount + "]";
	}
	
	public CustomerInventoryVisitDTO() {}
	
	public CustomerInventoryVisitDTO(Integer customerId, String customerCode, String customerName, Integer agentId,
			String agentCode, LocalDate visitDate, Long productCount) {
		super();
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.agentId = agentId;
		this.agentCode = agentCode;
		this.visitDate = visitDate;
		this.productCount = productCount;
	}

}
