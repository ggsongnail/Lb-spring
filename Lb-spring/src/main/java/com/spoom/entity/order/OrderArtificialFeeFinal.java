package com.spoom.entity.order;

import java.io.Serializable;

public class OrderArtificialFeeFinal implements Serializable {
	private int id;
	private String name;
	private String standard;
	private Double price;
	private Integer count;
	private Double total;
	private Double totalReal;//人工费单独需要此参数
	private Integer difCount;
	private Double difTotal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTotalReal() {
		return totalReal;
	}
	public void setTotalReal(Double totalReal) {
		this.totalReal = totalReal;
	}
	public Integer getDifCount() {
		return difCount;
	}
	public void setDifCount(Integer difCount) {
		this.difCount = difCount;
	}
	public Double getDifTotal() {
		return difTotal;
	}
	public void setDifTotal(Double difTotal) {
		this.difTotal = difTotal;
	}
}
