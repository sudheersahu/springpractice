package com.hellokoding.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hellokoding.auth.model.Product;


public interface ProductService {
	   void save(Product product );

	    Product findByProductCode(String productcode);
	    
	    List<Product> findAllProducts();
	    
}
