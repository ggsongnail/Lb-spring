package com.spoom.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderArtificialFeeFinal implements Serializable {
	private int mId;//artificialId
	private int oId;//orderArtificialId
	private int orderId;
	private String name;
	private String style;
	private String standard;
	private BigDecimal price;
	private Integer count;
	private BigDecimal total;
	private BigDecimal totalReal;//人工费单独需要此参数
	private Integer difCount;
	private BigDecimal difTotal;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTotalReal() {
		return totalReal;
	}
	public void setTotalReal(BigDecimal totalReal) {
		this.totalReal = totalReal;
	}
	public Integer getDifCount() {
		return difCount;
	}
	public void setDifCount(Integer difCount) {
		this.difCount = difCount;
	}
	public BigDecimal getDifTotal() {
		return difTotal;
	}
	public void setDifTotal(BigDecimal difTotal) {
		this.difTotal = difTotal;
	}
}
