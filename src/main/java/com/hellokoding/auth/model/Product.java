package com.hellokoding.auth.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

	@Id
    @Column(name = "Code", length = 20, nullable = false)
    private String code;
 
    @Column(name = "Name", length = 255, nullable = false)
    private String name;
 
    @Column(name = "Price", nullable = false)
    private double price;
 
    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
     
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
    private Date createDate;

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", price=" + price + ", image=" + Arrays.toString(image)
				+ ", createDate=" + createDate + "]";
	}
}
