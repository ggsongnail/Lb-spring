package com.spoom.entity.admin;

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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.order.OrderArtificial;
import com.spoom.entity.order.OrderProduct;

@Entity
@Table(name="admin_user")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class AdminUser {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String password;
	private String phone;
	private Integer role;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	private Integer status;
	@Version
	private Integer version;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
