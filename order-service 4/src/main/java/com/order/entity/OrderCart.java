package com.order.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class OrderCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private long  productId;
	private String productName;
	private long userId;
	private long crustId;
	private long orderCount;
	private long amount;
	private String size;
	private String imageUrl;
	private String description;
	public OrderCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderCart(Long id, long productId, String productName, long userId, long crustId, long orderCount,
			long amount, String size, String imageUrl, String description) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.userId = userId;
		this.crustId = crustId;
		this.orderCount = orderCount;
		this.amount = amount;
		this.size = size;
		this.imageUrl = imageUrl;
		this.description = description;
	}
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() { 
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCrustId() {
		return crustId;
	}
	public void setCrustId(long crustId) {
		this.crustId = crustId;
	}
	public long getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
