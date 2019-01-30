package com.hellokoding.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellokoding.auth.model.Product;


public interface ProductRepository extends JpaRepository<Product, String>{
	
	Product findByCode(String code);
	
}
