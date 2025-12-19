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
import org.springframework.stereotype.Service;

import com.soses.multilines.common.MappingUtil;
import com.soses.multilines.dto.PrivilegeViewDTO;
import com.soses.multilines.dto.RoleDTO;
import com.soses.multilines.dto.UserDTO;
import com.soses.multilines.dto.UserDetailsDTO;
import com.soses.multilines.entity.Privilege;
import com.soses.multilines.entity.User;
import com.soses.multilines.repository.PrivilegeRepository;
import com.soses.multilines.repository.RoleRepository;
import com.soses.multilines.repository.UserPrivilegeRepository;
import com.soses.multilines.repository.UserRepository;
import com.soses.multilines.repository.UserRoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserViewDetailsService {

	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private UserRoleRepository userRoleRepo;
	
	private UserPrivilegeRepository userPrivRepo;
	
	private PrivilegeRepository privRepo;

	public UserViewDetailsService(UserRepository userRepository, RoleRepository roleRepository,
			UserRoleRepository userRoleRepo, UserPrivilegeRepository userPrivRepo, PrivilegeRepository privRepo) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepo = userRoleRepo;
		this.userPrivRepo = userPrivRepo;
		this.privRepo = privRepo;
	}

	public UserDetailsDTO getUserDetailsView(Integer userId) throws Exception {
    	
    	Optional<User> u = userRepository.findById(userId);
    	if (u.isEmpty()) {
    		throw new Exception("User not found");
    	}
    	User user = u.get();
    	
    	UserDetailsDTO dto = new UserDetailsDTO();
    	
    	UserDTO form = new UserDTO();
    	form.setUserCode(user.getUserCode());
    	form.setUsername(user.getUserCode());
    	form.setLastName(user.getUserCode());
    	form.setFirstName(user.getUserCode());
    	form.setTerminationDate(user.getTerminationDate());
    	form.setUpdateTimestamp(user.getUpdateTimestamp());
    	form.setEntryTimestamp(user.getEntryTimestamp());
    	dto.setUserDTO(form);
    	
    	
    	Set<Integer> assignedRoleIds = userRoleRepo.findActiveRoleIdsByUser(userId);
    	
    	List<RoleDTO> roleDTOList = (List<RoleDTO>) roleRepository.findAvailableRole().stream().map(r -> {
    		RoleDTO roleDTO = MappingUtil.toRoleDTO(r);
    		roleDTO.setAssigned(assignedRoleIds.contains(r.getRoleId()));
    		return roleDTO;
    	}).collect(Collectors.toList());
    	dto.setRoles(roleDTOList);
    	
    	Set<Integer> assignedPrivilegeIds = userPrivRepo.findActivePrivilegeIdsByUser(userId);
    	Map<String, List<PrivilegeViewDTO>> privilegeMap = new LinkedHashMap<>();
    	for (Privilege p : privRepo.findAllActive()) {
    		
    		PrivilegeViewDTO privDTO = MappingUtil.toPrivilegeView(p);
    		privDTO.setAssigned(assignedPrivilegeIds.contains(p.getPrivilegeId()));

            privilegeMap
                .computeIfAbsent(p.getPrivilegeModule(), k -> new ArrayList<>())
                .add(privDTO);
    	}
    	dto.setPrivilegeByModule(privilegeMap);	
    	
    	return dto;
    }
}
