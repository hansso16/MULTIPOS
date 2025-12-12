package com.soses.multilines.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.CustomerInventory;
import com.soses.multilines.entity.CustomerInventoryPK;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerInventoryRepository extends JpaRepository<CustomerInventory, CustomerInventoryPK> {
	
	@Query("""
			SELECT i FROM CustomerInventory i 
	        WHERE i.id.customerId = :customerId 
	          AND i.id.productId = :productId
	          AND i.id.visitDate = :visitDate
			""")
	List<CustomerInventory> findByIdCustomerIdAndIdProductIdAndIdVisitDate(
	        @Param("customerId") Integer customerId,
	        @Param("productId") Integer productId,
	        @Param("visitDate") LocalDate visitDate);
	
	@Query("""
	       SELECT i
	       FROM CustomerInventory i
	       WHERE i.id.customerId = :customerId
	         AND i.id.visitDate = :visitDate
	       """)
	List<CustomerInventory> findByCustomerAndVisitDate(
	        @Param("customerId") Integer customerId,
	        @Param("visitDate") LocalDate visitDate);
	
	@Query("""
	       SELECT i
	       FROM CustomerInventory i
	       WHERE i.id.customerId = :customerId
	         AND i.id.productId = :productId
	       ORDER BY i.id.visitDate DESC
	       """)
	List<CustomerInventory> findPreviousInventory(
	        @Param("customerId") Integer customerId,
	        @Param("productId") Integer productId);
	
	boolean existsByIdCustomerIdAndIdVisitDate(
	        Integer customerId,
	        LocalDate visitDate);
	
	@Transactional
	void deleteByIdCustomerIdAndIdVisitDate(Integer customerId, LocalDate visitDate);
	
	@Query("""
	    SELECT i FROM CustomerInventory i
	    WHERE i.id.customerId = :customerId
	      AND i.id.agentId = :agentId
	      AND i.id.visitDate = :visitDate
	""")
	List<CustomerInventory> findByCustomerAgentVisitDate(
	        @Param("customerId") Integer customerId,
	        @Param("agentId") Integer agentId,
	        @Param("visitDate") LocalDate visitDate
	);
	        
}
