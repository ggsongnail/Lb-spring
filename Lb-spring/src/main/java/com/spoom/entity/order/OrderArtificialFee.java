package com.spoom.entity.order;

import java.util.Date;

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

import com.spoom.entity.artificial.ArtificialFee;

@Entity
@Table(name="order_artificial")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class OrderArtificialFee {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderLb orderLb;
	@ManyToOne
	@JoinColumn(name="artificial_id")
	private ArtificialFee artificialFee;
	private Integer count;
	private Double total;
	private Double totalReal;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
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
	public ArtificialFee getArtificialFee() {
		return artificialFee;
	}
	public void setArtificialFee(ArtificialFee artificialFee) {
		this.artificialFee = artificialFee;
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
