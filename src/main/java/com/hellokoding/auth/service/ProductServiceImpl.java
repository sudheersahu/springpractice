package com.hellokoding.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellokoding.auth.model.Product;
import com.hellokoding.auth.repository.ProductRepository;

@Service
public class ProductServiceImpl  implements ProductService{

	@Autowired
	ProductRepository repository;
	
	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findByProductCode(String productcode) {
		// TODO Auto-generated method stub
		return repository.findByCode(productcode);
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
