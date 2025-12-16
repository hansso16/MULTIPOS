package com.soses.multilines.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Page<Customer> findByCustomerCodeContainsOrCustomerNameContains(String customerId, String customerName, Pageable page);
	
	@Query("""
        SELECT c FROM Customer c
        WHERE c.id NOT IN (
            SELECT ac.id.customerId
            FROM AgentCustomer ac
            WHERE ac.id.agentId = :agentId
        )
    """)
    List<Customer> findAvailableForAgent(@Param("agentId") String agentId);
	
	
	@Query("""
        SELECT c FROM Customer c
        WHERE 
			(
				:searchText IS NULL
	            OR :searchText = ''
	            OR c.customerCode LIKE CONCAT('%', :searchText, '%')
	            OR c.customerName LIKE CONCAT('%', :searchText, '%')
			) AND
			c.id IN (
	            SELECT ac.id.customerId
	            FROM AgentCustomer ac
	            WHERE ac.id.agentId = :agentId
			)
    """)
    Page<Customer> findAssignedForAgent(@Param("agentId") Integer agentId, @Param("searchText") String searchText, Pageable page);
	

}
