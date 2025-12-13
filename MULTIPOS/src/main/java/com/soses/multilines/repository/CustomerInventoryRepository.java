package com.soses.multilines.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soses.multilines.dto.CustomerInventoryVisitDTO;
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
	int deleteByIdCustomerIdAndIdAgentIdAndIdVisitDate(Integer customerId, Integer agentId, LocalDate visitDate);
	
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
	
	@Query("""
	        SELECT new com.soses.multilines.dto.CustomerInventoryVisitDTO(
	            c.customerId
	            , c.customerCode
	            , c.customerName
	            , a.agentId
	            , a.agentCode
	            , ci.id.visitDate,
	            COUNT(ci)
	        )
	        FROM CustomerInventory ci
	        JOIN ci.customer c
	        JOIN ci.agent a
	        GROUP BY c.customerId, a.agentId, ci.id.visitDate
	        ORDER BY ci.id.visitDate DESC
	    """)
	Page<CustomerInventoryVisitDTO> findDistinctVisits(Pageable page);
	
	@Query("""
	        SELECT new com.soses.multilines.dto.CustomerInventoryVisitDTO(
	            c.customerId
	            , c.customerCode
	            , c.customerName
	            , a.agentId
	            , a.agentCode
	            , ci.id.visitDate,
	            COUNT(ci)
	        )
	        FROM CustomerInventory ci
	        JOIN ci.customer c
	        JOIN ci.agent a
	        WHERE c.customerCode LIKE CONCAT('%',:customerCode,'%') OR a.agentCode LIKE CONCAT('%',:agentCode,'%')
	        GROUP BY c.customerId, a.agentId, ci.id.visitDate
	        ORDER BY ci.id.visitDate DESC
	    """)
	Page<CustomerInventoryVisitDTO> findDistinctVisitsByCustomerCodeOrAgentCode(
			@Param("customerCode") String customerCode, 
			@Param("agentCode") String agentCode, 
			Pageable page);
	
	List<CustomerInventory> findByIdCustomerIdAndIdAgentIdAndIdVisitDate(
	    Integer customerId,
	    Integer agentId,
	    LocalDate visitDate
	);
	        
}
