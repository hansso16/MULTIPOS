package com.soses.multilines.api.user;

import java.util.List;
import java.util.Map;

import com.soses.multilines.dto.EditUserDTO;
import com.soses.multilines.dto.PrivilegeViewDTO;
import com.soses.multilines.dto.RoleDTO;

public class EditUserRequest {

	EditUserDTO editUserDto;
	
	Map<String, List<PrivilegeViewDTO>> privilegeByModule;
	
	List<RoleDTO> roleList;

	public EditUserDTO getEditUserDto() {
		return editUserDto;
	}

	public void setEditUserDto(EditUserDTO editUserDto) {
		this.editUserDto = editUserDto;
	}

	public Map<String, List<PrivilegeViewDTO>> getPrivilegeByModule() {
		return privilegeByModule;
	}

	public void setPrivilegeByModule(Map<String, List<PrivilegeViewDTO>> privilegeByModule) {
		this.privilegeByModule = privilegeByModule;
	}

	public List<RoleDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDTO> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "EditUserRequest [editUserDto=" + editUserDto + ", privilegeByModule=" + privilegeByModule
				+ ", roleList=" + roleList + "]";
	}
	
}
