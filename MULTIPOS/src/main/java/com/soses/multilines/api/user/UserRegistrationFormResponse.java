package com.soses.multilines.api.user;

import java.util.List;
import java.util.Map;

import com.soses.multilines.dto.PrivilegeViewDTO;
import com.soses.multilines.entity.Role;

public class UserRegistrationFormResponse {

	UserRegistrationRequest request;
	
	Map<String, List<PrivilegeViewDTO>> privilegeByModule;
	
	List<Role> roleList;

	@Override
	public String toString() {
		return "UserRegistrationFormResponse [request=" + request + ", privilegeByModule=" + privilegeByModule
				+ ", roleList=" + roleList + "]";
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public UserRegistrationRequest getRequest() {
		return request;
	}

	public void setRequest(UserRegistrationRequest request) {
		this.request = request;
	}

	public Map<String, List<PrivilegeViewDTO>> getPrivilegeByModule() {
		return privilegeByModule;
	}

	public void setPrivilegeByModule(Map<String, List<PrivilegeViewDTO>> privilegeByModule) {
		this.privilegeByModule = privilegeByModule;
	}
}
