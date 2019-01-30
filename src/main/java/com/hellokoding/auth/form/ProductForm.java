package com.hellokoding.auth.form;

import org.springframework.web.multipart.MultipartFile;

import com.hellokoding.auth.model.Product;



public class ProductForm {
	
	public ProductForm(String code, String name, double price, boolean newProduct, MultipartFile fileData) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.newProduct = newProduct;
		this.fileData = fileData;
	}
	
	  public ProductForm(Product product) {
	        this.code = product.getCode();
	        this.name = product.getName();
	        this.price = product.getPrice();
	    }

	public ProductForm() {
		this.newProduct =true;

	}

	private String code;
	private String name;
	private double price;
	
	private boolean newProduct = false;
	
	private MultipartFile fileData;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}

}
