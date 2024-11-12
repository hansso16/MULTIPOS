package com.soses.multilines.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soses.multilines.entity.Role;
import com.soses.multilines.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
	
	public List<Role> findAllRoles() {
		List<Role> roleList = roleRepository.findAll();
		
		return roleList;
	}
}
