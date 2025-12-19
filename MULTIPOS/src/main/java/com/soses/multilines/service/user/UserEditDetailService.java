package com.soses.multilines.service.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soses.multilines.api.user.EditUserRequest;
import com.soses.multilines.common.GeneralUtil;
import com.soses.multilines.common.MappingUtil;
import com.soses.multilines.dto.EditUserDTO;
import com.soses.multilines.dto.PrivilegeViewDTO;
import com.soses.multilines.dto.RoleDTO;
import com.soses.multilines.entity.Privilege;
import com.soses.multilines.entity.User;
import com.soses.multilines.entity.UserPrivilege;
import com.soses.multilines.entity.UserPrivilegePK;
import com.soses.multilines.entity.UserRole;
import com.soses.multilines.entity.UserRolePK;
import com.soses.multilines.repository.PrivilegeRepository;
import com.soses.multilines.repository.RoleRepository;
import com.soses.multilines.repository.UserPrivilegeRepository;
import com.soses.multilines.repository.UserRepository;
import com.soses.multilines.repository.UserRoleRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserEditDetailService {

private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private UserRoleRepository userRoleRepo;
	
	private UserPrivilegeRepository userPrivRepo;
	
	private PrivilegeRepository privRepo;
	
	private PasswordEncoder passwordEncoder;

	public UserEditDetailService(UserRepository userRepository, RoleRepository roleRepository,
			UserRoleRepository userRoleRepo, UserPrivilegeRepository userPrivRepo, PrivilegeRepository privRepo,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepo = userRoleRepo;
		this.userPrivRepo = userPrivRepo;
		this.privRepo = privRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public EditUserRequest getUserEditDetailsView(Integer userId) throws Exception {
    	
    	Optional<User> u = userRepository.findById(userId);
    	if (u.isEmpty()) {
    		throw new Exception("User not found");
    	}
    	User user = u.get();
    	
    	EditUserRequest dto = new EditUserRequest();
    	
    	EditUserDTO form = new EditUserDTO();
    	form.setUserId(userId);
    	form.setUserCode(user.getUsername());
    	form.setUsername(user.getUsername());
    	form.setLastName(user.getLastName());
    	form.setFirstName(user.getFirstName());
    	form.setTerminationDate(user.getTerminationDate());
    	
    	
    	Set<Integer> assignedRoleIds = userRoleRepo.findActiveRoleIdsByUser(userId);
    	
    	List<RoleDTO> roleDTOList = (List<RoleDTO>) roleRepository.findAvailableRole().stream().map(r -> {
    		RoleDTO roleDTO = MappingUtil.toRoleDTO(r);
    		roleDTO.setAssigned(assignedRoleIds.contains(r.getRoleId()));
    		return roleDTO;
    	}).collect(Collectors.toList());
    	form.setRoleIds(new ArrayList<>(assignedRoleIds));
    	dto.setRoleList(roleDTOList);
    	
    	Set<Integer> assignedPrivilegeIds = userPrivRepo.findActivePrivilegeIdsByUser(userId);
    	
    	Map<String, List<PrivilegeViewDTO>> privilegeMap = new LinkedHashMap<>();
    	for (Privilege p : privRepo.findAllActive()) {
    		
    		PrivilegeViewDTO privDTO = MappingUtil.toPrivilegeView(p);
    		privDTO.setAssigned(assignedPrivilegeIds.contains(p.getPrivilegeId()));

            privilegeMap
                .computeIfAbsent(p.getPrivilegeModule(), k -> new ArrayList<>())
                .add(privDTO);
    	}
    	form.setPrivilegeIds(new ArrayList<>(assignedPrivilegeIds));
    	dto.setPrivilegeByModule(privilegeMap);	
    	dto.setEditUserDto(form);
    	
    	return dto;
    }
	
	public void updateUser(EditUserDTO form) throws Exception {

	    Optional<User> u = userRepository.findById(form.getUserId());
	    if (u.isEmpty()) {
	    	throw new Exception("No user found..");
	    }

	    User user = u.get();
	    user.setFirstName(form.getFirstName());
	    user.setLastName(form.getLastName());
	    user.setTerminationDate(form.getTerminationDate());

	    if (!StringUtils.isEmpty(form.getNewPassword())) {
	        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
	            throw new Exception("Passwords do not match");
	        }
	        user.setPassword(passwordEncoder.encode(form.getNewPassword()));
	    }

	    // 3. Update roles (simple approach)
	    List<Integer> roleList = form.getRoleIds();
	    if (!GeneralUtil.isListEmpty(roleList)) {
	    	userRoleRepo.deleteActiveRolesByUser(user.getUserId());
	    	assignRoles(user, form.getRoleIds());
	    }

	    // 4. Update privileges
	    List<Integer> privilegeList = form.getPrivilegeIds();
	    if (!GeneralUtil.isListEmpty(privilegeList)) {
	    	userPrivRepo.deleteActivePrivilegesByUser(user.getUserId());
	    	assignPrivileges(user, form.getPrivilegeIds());
	    }
	}
	
	private void assignRoles(User user, List<Integer> roleIds) {

	    for (Integer roleId : roleIds) {
	        UserRole ur = new UserRole();
	        UserRolePK id = new UserRolePK();
	        id.setUserId(user.getUserId());
	        id.setRoleId(roleId);
	        ur.setId(id);
//	        ur.setStartLocalDate(LocalDate.now());
//	        ur.setEndLocalDate(LocalDate.of(9999, 12, 31));
	        userRoleRepo.save(ur);
	    }
	}
	
	private void assignPrivileges(User user, List<Integer> privilegeIds) {

	    for (Integer privilegeId : privilegeIds) {
	        UserPrivilege up = new UserPrivilege();
	        UserPrivilegePK id = new UserPrivilegePK();
	        id.setUserId(user.getUserId());
	        id.setPrivilegeId(privilegeId);
	        up.setId(id);
//	        up.setStartLocalDate(LocalDate.now());
//	        up.setEndLocalDate(LocalDate.of(9999, 12, 31));
	        userPrivRepo.save(up);
	    }
	}
}
