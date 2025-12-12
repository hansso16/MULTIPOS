package com.soses.multilines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Agent;
import com.soses.multilines.entity.AgentCustomer;
import com.soses.multilines.entity.AgentCustomerPK;
import com.soses.multilines.entity.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface AgentCustomerRepository extends JpaRepository<AgentCustomer, AgentCustomerPK> {

    // ✅ Assigned customers for one agent
    @Query("""
        SELECT ac.customer
        FROM AgentCustomer ac
        WHERE ac.id.agentId = :agentId
    """)
    List<Customer> findAssignedCustomers(@Param("agentId") Integer agentId);

    // ✅ Assigned agents for one customer (if needed)
    @Query("""
        SELECT ac.agent
        FROM AgentCustomer ac
        WHERE ac.id.customerId = :customerId
    """)
    List<Agent> findAssignedAgents(@Param("customerId") Integer customerId);

    // ✅ Remove all assignments for an agent (SAVE flow)
    @Modifying
    @Transactional
    @Query("""
        DELETE FROM AgentCustomer ac
        WHERE ac.id.agentId = :agentId
    """)
    void deleteByAgentId(@Param("agentId") Integer agentId);

    // ✅ Check if assignment exists
    boolean existsByIdAgentIdAndIdCustomerId(Integer agentId, Integer customerId);
    
    
    @Modifying
    @Query(value = """
        INSERT INTO agent_customer (agent_id, customer_id)
        VALUES (:agent_id, :customer_id)
        """, nativeQuery = true)
    void insert(@Param("agent_id") Integer agentId,
                @Param("customer_id") Integer customerId);
}
