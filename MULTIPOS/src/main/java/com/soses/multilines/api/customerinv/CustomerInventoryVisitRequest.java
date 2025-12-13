package com.soses.multilines.api.customerinv;

import com.soses.multilines.api.BaseSearchRequest;

public class CustomerInventoryVisitRequest extends BaseSearchRequest {

	private String agentCode;
	
	private String customerCode;

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	@Override
	public String toString() {
		return "CustomerInventoryVisitRequest [agentCode=" + agentCode + ", customerCode=" + customerCode + "]";
	}

	public CustomerInventoryVisitRequest(String agentCode, String customerCode) {
		super();
		this.agentCode = agentCode;
		this.customerCode = customerCode;
	}
	
}
