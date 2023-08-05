package com.pizzahub.coupon.entity;


import jakarta.persistence.*;

@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String couponName;
	private String couponDesc;
	private long couponPrice;
	private long minOrderAmount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponDesc() {
		return couponDesc;
	}
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	public long getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(long couponPrice) {
		this.couponPrice = couponPrice;
	}
	public long getMinOrderAmount() {
		return minOrderAmount;
	}
	public void setMinOrderAmount(long minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}
	public Coupon(long id, String couponName, String couponDesc, long couponPrice, long minOrderAmount) {
		super();
		this.id = id;
		this.couponName = couponName;
		this.couponDesc = couponDesc;
		this.couponPrice = couponPrice;
		this.minOrderAmount = minOrderAmount;
	}
	public Coupon() {
		
	}
	
	
	
	 

}
