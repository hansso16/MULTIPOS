package com.soses.multilines.api.customer;

import com.soses.multilines.api.BaseSearchRequest;

public class CustomerSearchRequest extends BaseSearchRequest {

	private String customerId;
	
	private String agentId;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CustomerSearchRequest [customerId=" + customerId + ", agentId=" + agentId + "]";
	}
	
}
