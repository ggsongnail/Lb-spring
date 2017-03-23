package com.spoom.entity.order;

import javax.persistence.Cacheable;
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

import com.spoom.entity.material.MaterialSets;

@Entity
@Table(name="order_sets")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class OrderSets {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderLb orderLb;
	@ManyToOne
	@JoinColumn(name="sets_id")
	private MaterialSets materialSets;
	private Integer count;
	private Double total;
	private Integer status;
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
	public MaterialSets getMaterialSets() {
		return materialSets;
	}
	public void setMaterialSets(MaterialSets materialSets) {
		this.materialSets = materialSets;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
