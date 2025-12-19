package com.soses.multilines.dto;

import java.util.List;
import java.util.Map;

public class UserDetailsDTO {

	private UserDTO userDTO;
	
	private List<RoleDTO> roles;
	
	private Map<String, List<PrivilegeViewDTO>> privilegeByModule;

	
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public Map<String, List<PrivilegeViewDTO>> getPrivilegeByModule() {
		return privilegeByModule;
	}

	public void setPrivilegeByModule(Map<String, List<PrivilegeViewDTO>> privilegeByModule) {
		this.privilegeByModule = privilegeByModule;
	}

	@Override
	public String toString() {
		return "UserDetailsDTO [userDTO=" + userDTO + ", roles=" + roles + ", privilegeByModule=" + privilegeByModule
				+ "]";
	}
	
	
}
