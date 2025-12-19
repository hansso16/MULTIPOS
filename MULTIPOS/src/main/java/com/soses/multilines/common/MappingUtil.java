package com.soses.multilines.common;

import com.soses.multilines.dto.PrivilegeViewDTO;
import com.soses.multilines.dto.RoleDTO;
import com.soses.multilines.entity.Privilege;
import com.soses.multilines.entity.Role;

public class MappingUtil {

	public static RoleDTO toRoleDTO(Role r) {
		RoleDTO rv = new RoleDTO();
	    rv.setRoleId(r.getRoleId());
	    rv.setRoleName(r.getRoleName());
	    rv.setRoleDescription(r.getRoleDescription());
	    return rv;
	}
	
	public static PrivilegeViewDTO toPrivilegeView(Privilege p) {

		PrivilegeViewDTO pv = new PrivilegeViewDTO();
	    pv.setPrivilegeId(p.getPrivilegeId());
	    pv.setPrivilegeName(p.getPrivilegeName());
	    pv.setPrivilegeDescription(p.getPrivilegeDescription());
	    pv.setPrivilegeModule(p.getPrivilegeModule());

	    return pv;
	}
}
