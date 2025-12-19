package com.soses.multilines.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.UserPrivilege;
import com.soses.multilines.entity.UserPrivilegePK;

@Repository
public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, UserPrivilegePK> {

	@Query("""
	        SELECT u.id.privilegeId FROM UserPrivilege u
	        WHERE u.id.userId = :userId
	    """)
	Set<Integer> findActivePrivilegeIdsByUser(Integer userId);
	
	@Modifying
	@Query("""
		DELETE FROM UserPrivilege up
		WHERE up.id.userId = :userId
	""")
	void deleteActivePrivilegesByUser(Integer userId);
}
