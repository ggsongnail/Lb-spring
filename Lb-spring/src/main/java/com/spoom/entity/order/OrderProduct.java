package com.spoom.entity.order;

import java.math.BigDecimal;
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
	/*@ManyToOne
	@JoinColumn(name="order_id")
	private OrderLb orderLb;*/
	/*@ManyToOne
	@JoinColumn(name="product_id")
	private MaterialProduct materialProduct;*/
	private Integer orderId;
	private Integer productId;
	private String materialClassifyName;
    private String productName;
    private String standard;
    private BigDecimal price;
    private Integer place;
    private Integer dictionaryClassifyId;
	private Integer count;//数量
	private Double total;//总额  = 数量 x 定义材料单价
	private Integer difCount;//决算变更数量
	private Double difTotal;//决算变更总额
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
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getMaterialClassifyName() {
		return materialClassifyName;
	}
	public void setMaterialClassifyName(String materialClassifyName) {
		this.materialClassifyName = materialClassifyName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getPlace() {
		return place;
	}
	public void setPlace(Integer place) {
		this.place = place;
	}
	public Integer getDictionaryClassifyId() {
		return dictionaryClassifyId;
	}
	public void setDictionaryClassifyId(Integer dictionaryClassifyId) {
		this.dictionaryClassifyId = dictionaryClassifyId;
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
