package com.cart.entity;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Cart {
	
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
	//private String type;
	private String imageUrl;
	private String description;

}
