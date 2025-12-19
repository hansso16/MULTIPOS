package com.soses.multilines.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Role;

@Repository
public interface UserPrivilegeRepository extends JpaRepository<Role, Integer> {

	@Query("""
	        SELECT u.id.privilegeId FROM UserPrivilege u
	        WHERE u.id.userId = :userId
	    """)
	Set<Integer> findActivePrivilegeIdsByUser(Integer userId);
}
