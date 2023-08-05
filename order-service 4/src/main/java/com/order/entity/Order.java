package com.order.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long Id;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="orders",referencedColumnName = "id")
	 private List<OrderCart> cart;
	 private long userId;
	 private Date date;
	 private String status;
	 private double  amount;
	 private String address;
	public Order() {
		super(); 
		// TODO Auto-generated constructor stub
	}
	public Order(Long id, List<OrderCart> cart, long userId, Date date, String status, double amount, String address) {
		super();
		Id = id;
		this.cart = cart;
		this.userId = userId;
		this.date = date;
		this.status = status;
		this.amount = amount;
		this.address = address;
	}
	
	public List<OrderCart> getCart() {
		return cart;
	}
	public void setCart(List<OrderCart> cart) {
		this.cart = cart;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	 
}
