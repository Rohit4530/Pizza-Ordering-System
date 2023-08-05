package com.sides.entity;

import jakarta.persistence.*;

@Entity
public class Sides {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String sideName;
	private String sideType;
	private long sidePrice;
	private Long sideCount;
	private String sideDesc;
	private String imgURL;
	
	public Sides() {
		super();
	}
	public Sides(long id, String sideName, String sideType, long sidePrice, Long sideCount, String sideDesc,
			String imgURL) {
		super();
		this.id = id;
		this.sideName = sideName;
		this.sideType = sideType;
		this.sidePrice = sidePrice;
		this.sideCount = sideCount;
		this.sideDesc = sideDesc;
		this.imgURL = imgURL;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSideName() {
		return sideName;
	}
	public void setSideName(String sideName) {
		this.sideName = sideName;
	}
	public String getSideType() {
		return sideType;
	}
	public void setSideType(String sideType) {
		this.sideType = sideType;
	}
	public long getSidePrice() {
		return sidePrice;
	}
	public void setSidePrice(long sidePrice) {
		this.sidePrice = sidePrice;
	}
	public Long getSideCount() {
		return sideCount;
	}
	public void setSideCount(Long sideCount) {
		this.sideCount = sideCount;
	}
	public String getSideDesc() {
		return sideDesc;
	}
	public void setSideDesc(String sideDesc) {
		this.sideDesc = sideDesc;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	
	
	
}
