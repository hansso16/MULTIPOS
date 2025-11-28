package com.soses.multilines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

}
