package com.soses.multilines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// Available products
    @Query("""
        SELECT p FROM Product p
        WHERE p.productId NOT IN (
            SELECT cp.id.productId
            FROM CustomerProduct cp
            WHERE cp.id.customerId = :customerId
        )
    """)
    List<Product> findProductsNotAssignedToCustomer(String customerId);
 
}
