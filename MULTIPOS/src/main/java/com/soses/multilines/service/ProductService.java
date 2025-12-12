package com.soses.multilines.service;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.soses.multilines.entity.Product;
import com.soses.multilines.repository.CustomerProductRepository;
import com.soses.multilines.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProductService {

	private final ProductRepository productRepo;
	
	private final CustomerProductRepository customerProductRepo;

	public ProductService(ProductRepository productRepo, CustomerProductRepository customerProductRepo) {
		super();
		this.productRepo = productRepo;
		this.customerProductRepo = customerProductRepo;
	}
	
	public Product findById(Integer id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public List<Product> getAssignedProducts(Integer customerId) {
        return customerProductRepo.findProductsByCustomerId(customerId);
    }
}
