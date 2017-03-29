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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="order_refuse")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class OrderRefuse {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderLb orderLb;
	private String people;
	@Temporal(TemporalType.TIMESTAMP)//入库时分秒
	@JsonFormat(pattern="yyyy/MM/dd HH:mm",timezone = "GMT+8") 
	private Date talkTime;
	private String talkWay;
	private String reason;
	private String plan;
	@Version
	private Integer version;
	public int getId() {
		return id;
	}
	public OrderLb getOrderLb() {
		return orderLb;
	}
	public String getPeople() {
		return people;
	}
	public Date getTalkTime() {
		return talkTime;
	}
	
	public String getTalkWay() {
		return talkWay;
	}
	public void setTalkWay(String talkWay) {
		this.talkWay = talkWay;
	}
	public String getReason() {
		return reason;
	}
	public String getPlan() {
		return plan;
	}
	public Integer getVersion() {
		return version;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOrderLb(OrderLb orderLb) {
		this.orderLb = orderLb;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public void setTalkTime(Date talkTime) {
		this.talkTime = talkTime;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
