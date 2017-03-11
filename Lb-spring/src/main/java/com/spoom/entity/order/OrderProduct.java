package com.spoom.entity.order;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.spoom.entity.material.MaterialProduct;

@Entity
@Table(name="order_product")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class OrderProduct {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderLb orderLb;
	@ManyToOne
	@JoinColumn(name="product_id")
	private MaterialProduct materialProduct;
	private int count;
	private double total;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int status;
	@Version
	private Integer version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrderLb getOrderLb() {
		return orderLb;
	}
	public void setOrderLb(OrderLb orderLb) {
		this.orderLb = orderLb;
	}
	public MaterialProduct getMaterialProduct() {
		return materialProduct;
	}
	public void setMaterialProduct(MaterialProduct materialProduct) {
		this.materialProduct = materialProduct;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
