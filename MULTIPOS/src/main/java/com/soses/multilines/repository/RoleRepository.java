package com.soses.multilines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findAll();
}
