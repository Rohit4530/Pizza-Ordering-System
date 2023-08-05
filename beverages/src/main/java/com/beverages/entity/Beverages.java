package com.beverages.entity;


import jakarta.persistence.*;

@Entity
public class Beverages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String beverageName;
	private String beverageQuant;
	private String beverageDisc;
	private long beveragePrice;
	private Long beverageCount;
	private String imgURL;
	
	
	public Beverages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Beverages(long id, String beverageName, String beverageQuant, String beverageDisc, long beveragePrice,
			Long beverageCount, String imgURL) {
		super();
		this.id = id;
		this.beverageName = beverageName;
		this.beverageQuant = beverageQuant;
		this.beverageDisc = beverageDisc;
		this.beveragePrice = beveragePrice;
		this.beverageCount = beverageCount;
		this.imgURL = imgURL;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBeverageName() {
		return beverageName;
	}
	public void setBeverageName(String beverageName) {
		this.beverageName = beverageName;
	}
	public String getBeverageQuant() {
		return beverageQuant;
	}
	public void setBeverageQuant(String beverageType) {
		this.beverageQuant = beverageType;
	}
	public long getBeveragePrice() {
		return beveragePrice;
	}
	public void setBeveragePrice(long beveragePrice) {
		this.beveragePrice = beveragePrice;
	}
	public Long getBeverageCount() {
		return beverageCount;
	}
	public void setBeverageCount(Long beverageCount) {
		this.beverageCount = beverageCount;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getBeverageDisc() {
		return beverageDisc;
	}

	public void setBeverageDisc(String beverageDisc) {
		this.beverageDisc = beverageDisc;
	}
	
	


	
	
}
