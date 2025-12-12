package com.soses.multilines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.CustomerProduct;
import com.soses.multilines.entity.CustomerProductPK;
import com.soses.multilines.entity.Product;

@Repository
public interface CustomerProductRepository extends JpaRepository<CustomerProduct, CustomerProductPK> {
	
	@Modifying
    @Query("DELETE FROM CustomerProduct cp WHERE cp.id.customerId = :customerId")
    void deleteByCustomerId(@Param("customerId") Integer customerId);
	
	@Modifying
    @Query(value = """
        INSERT INTO customer_product (customer_id, product_id)
        VALUES (:customerId, :productId)
        """, nativeQuery = true)
    void insert(@Param("customerId") Integer customerId,
                @Param("productId") Integer productId);

	@Query("""
    	    SELECT cp.product 
    	    FROM CustomerProduct cp
    	    WHERE cp.id.customerId = :customerId
    	""")
	List<Product> findProductsByCustomerId(@Param("customerId") Integer customerId);
}
