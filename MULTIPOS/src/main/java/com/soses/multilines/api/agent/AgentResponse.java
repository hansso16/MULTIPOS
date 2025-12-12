package com.soses.multilines.api.agent;

import java.util.List;

import com.soses.multilines.entity.Agent;

public class AgentResponse extends BaseAgentResponse {

	List<Agent> agentList;

	public List<Agent> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<Agent> agentList) {
		this.agentList = agentList;
	}

	@Override
	public String toString() {
		return "AgentResponse []";
	}
	
}
