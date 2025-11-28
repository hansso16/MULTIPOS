package com.soses.multilines.service.user.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soses.multilines.api.user.UserRegistrationRequest;
import com.soses.multilines.api.user.UserRegistrationResponse;
import com.soses.multilines.common.ErrorConstants;
import com.soses.multilines.common.GeneralUtil;
import com.soses.multilines.common.MessageConstants;
import com.soses.multilines.entity.Privilege;
import com.soses.multilines.entity.Role;
import com.soses.multilines.entity.User;
import com.soses.multilines.repository.PrivilegeRepository;
import com.soses.multilines.repository.RoleRepository;
import com.soses.multilines.repository.UserRepository;
import com.soses.multilines.service.user.UserRegistrationService;

import jakarta.transaction.Transactional;

@Service("UserRegistrationServiceImpl")
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private static final Logger log = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	    
	public UserRegistrationServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.privilegeRepository = privilegeRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserRegistrationResponse registerUser(UserRegistrationRequest request) {

		log.info("ENTER:registerUser(request)");
		UserRegistrationResponse response = new UserRegistrationResponse();

		if (!validateInputs(request, response)) {
			return response;
		}
		
		Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.getRoleIds()));
        Set<Privilege> privileges = new HashSet<>(privilegeRepository.findAllById(request.getPrivilegeIds()));
        
        User user = new User();
        user.setUserCode(request.getUserCode());
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt the password
        user.setRoleSet(roles);
        //user.setPrivileges(privileges);
        userRepository.save(user);
        response.setResponseMessage(MessageConstants.MESSAGE_USER_SAVED + request.getUsername());
        
        return response;
	}
	
    private boolean validateInputs(UserRegistrationRequest request, UserRegistrationResponse response) {
        // Check if username is already taken
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            response.setErrorMessage("Username is already taken.");
            return false;
        }

        // Check if the provided roles exist
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
        	List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        	if (GeneralUtil.isListEmpty(roles)) {
        		response.setErrorMessage(ErrorConstants.ERROR_ROLE_NOT_SELECTED);
        		return false;
        	}
        } else {
        	response.setErrorMessage(ErrorConstants.ERROR_ROLE_NOT_SELECTED);
        	return false;
        }

        // Check if the provided privileges exist
        if (request.getPrivilegeIds() != null && !request.getPrivilegeIds().isEmpty()) {
        	List<Privilege> privileges = privilegeRepository.findAllById(request.getPrivilegeIds());
        	if (GeneralUtil.isListEmpty(privileges)) {
        		response.setErrorMessage(ErrorConstants.ERROR_PRIVILEGE_NOT_SELECTED);
        		return false;
        	}
        } else {
        	response.setErrorMessage(ErrorConstants.ERROR_PRIVILEGE_NOT_SELECTED);
        	return false;
        }
        
        return true;
    }
}
