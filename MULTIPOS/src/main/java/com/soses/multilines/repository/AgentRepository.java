package com.soses.multilines.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Agent;

@Repository
public interface AgentRepository  extends JpaRepository<Agent, Integer> {
	
	Optional<Agent> findByUserId(Integer userId);
}
