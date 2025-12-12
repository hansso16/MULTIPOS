package com.soses.multilines.api.customer;

import com.soses.multilines.api.BaseResponse;

public class BaseCustomerResponse extends BaseResponse {

	private String customerCode;
	
	private String customerId;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "BaseCustomerResponse [customerCode=" + customerCode + ", customerId=" + customerId + "]";
	}
	
}
