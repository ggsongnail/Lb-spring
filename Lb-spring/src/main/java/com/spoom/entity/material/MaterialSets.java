package com.spoom.entity.material;

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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.order.OrderProduct;

@Entity
@Table(name="material_sets")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class MaterialSets {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="dictionary_classify_id")
	private DictionaryClassify dictionaryClassify;
	private String name;
	private String standard;//规格包装比如５L 5KG 平方数
	private Double price;
	private Double lowFee;
	private Double fiveFee;
	private Double tenFee;
	private Double fifthFee;
	private Double twthFee;
	private String remark;
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
	public DictionaryClassify getDictionaryClassify() {
		return dictionaryClassify;
	}
	public void setDictionaryClassify(DictionaryClassify dictionaryClassify) {
		this.dictionaryClassify = dictionaryClassify;
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
	public Double getLowFee() {
		return lowFee;
	}
	public void setLowFee(Double lowFee) {
		this.lowFee = lowFee;
	}
	public Double getFiveFee() {
		return fiveFee;
	}
	public void setFiveFee(Double fiveFee) {
		this.fiveFee = fiveFee;
	}
	public Double getTenFee() {
		return tenFee;
	}
	public void setTenFee(Double tenFee) {
		this.tenFee = tenFee;
	}
	public Double getFifthFee() {
		return fifthFee;
	}
	public void setFifthFee(Double fifthFee) {
		this.fifthFee = fifthFee;
	}
	public Double getTwthFee() {
		return twthFee;
	}
	public void setTwthFee(Double twthFee) {
		this.twthFee = twthFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
