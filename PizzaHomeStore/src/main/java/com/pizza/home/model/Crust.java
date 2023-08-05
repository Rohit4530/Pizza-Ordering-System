package com.pizza.home.model;

import jakarta.persistence.*;





@Entity
public class Crust {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer crustId;
	private String crustType;
	private double price;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "crust_id" ,referencedColumnName = "id")
//	private Pizza pizza;
	
	
	public Integer getCrustId() {
		return crustId;
	}
	public void setCrustId(Integer crustId) {
		this.crustId = crustId;
	}
	public String getCrustType() {
		return crustType;
	}
	public void setCrustType(String crustType) {
		this.crustType = crustType;
	}
	

}
