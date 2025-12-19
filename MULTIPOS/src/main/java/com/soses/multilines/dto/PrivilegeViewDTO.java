package com.soses.multilines.dto;

public class PrivilegeViewDTO {

	private Integer privilegeId;
    private String privilegeName;
    private String privilegeDescription;
    private String privilegeModule;
    private boolean assigned = false;

    public boolean isAssigned() {
		return assigned;
	}
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getPrivilegeDescription() {
		return privilegeDescription;
	}
	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}
	public String getPrivilegeModule() {
		return privilegeModule;
	}
	public void setPrivilegeModule(String privilegeModule) {
		this.privilegeModule = privilegeModule;
	}
	@Override
	public String toString() {
		return "PrivilegeViewDTO [privilegeId=" + privilegeId + ", privilegeName=" + privilegeName
				+ ", privilegeDescription=" + privilegeDescription + ", privilegeModule=" + privilegeModule
				+ ", assigned=" + assigned + "]";
	}
}
