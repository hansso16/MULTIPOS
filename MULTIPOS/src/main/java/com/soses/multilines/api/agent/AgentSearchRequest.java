package com.soses.multilines.api.agent;

import com.soses.multilines.api.BaseSearchRequest;

public class AgentSearchRequest extends BaseSearchRequest {

	private String agentId;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	@Override
	public String toString() {
		return "AgentSearchRequest [agentId=" + agentId + "]";
	}
	
}
