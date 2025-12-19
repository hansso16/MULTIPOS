package com.soses.multilines.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.UserRole;
import com.soses.multilines.entity.UserRolePK;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {

	@Query("""
	        SELECT u.id.roleId FROM UserRole u
	        WHERE u.id.userId = :userId
	    """)
	Set<Integer> findActiveRoleIdsByUser(Integer userId);
	
	@Modifying
	@Query("""
		DELETE FROM UserRole ur
		WHERE ur.id.userId = :userId
	""")
	void deleteActiveRolesByUser(Integer userId);
}
