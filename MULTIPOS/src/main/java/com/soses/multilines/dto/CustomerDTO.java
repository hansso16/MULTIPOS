package com.soses.multilines.dto;

public class CustomerDTO {

	private int customerId;
	
	private String customerCode;
	
	private String customerName;
	
	public CustomerDTO() {}
	
	public CustomerDTO(int customerId, String customerCode, String customerName) {
		super();
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.customerName = customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
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

	@Override
	public String toString() {
		return "CustomerDTO [customerId=" + customerId + ", customerCode=" + customerCode + ", customerName="
				+ customerName + "]";
	}
	
	
}
