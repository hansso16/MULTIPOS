package com.soses.multilines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

	
	@Query("""
	        SELECT p FROM Privilege p
	        WHERE CURRENT_DATE BETWEEN p.startLocalDate AND p.endLocalDate
	    """)
	List<Privilege> findAllActive();
}
