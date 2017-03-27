package com.spoom.entity.order;

import java.io.Serializable;

public class OrderProductFinal implements Serializable {
	private int mId;//materialProductId
	private int oId;//orderProductId
	private int orderId;
	private String name;
	private String standard;
	private Double price;
	private Integer count;
	private Double total;
	private Integer difCount;
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	private Double difTotal;
	
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
