package com.pizza.home.model;


import jakarta.persistence.*;
@Entity
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	private String pizzaName;
	private String pizzaType;
	private double pizzaPrice;
	private String pizzaDesc;
	private Long pizzaCount;
	private String imgURL;
	
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "crust_id")
	private Crust crust;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}
	public double getPizzaPrice() {
		return pizzaPrice;
	}
	public void setPizzaPrice(double pizzaPrice) {
		this.pizzaPrice = pizzaPrice;
	}
	public String getPizzaDesc() {
		return pizzaDesc;
	}
	public void setPizzaDesc(String pizzaDesc) {
		this.pizzaDesc = pizzaDesc;
	}
	public Long getPizzaCount() {
		return pizzaCount;
	}
	public void setPizzaCount(Long pizzaCount) {
		this.pizzaCount = pizzaCount;
	}
	
	
}
