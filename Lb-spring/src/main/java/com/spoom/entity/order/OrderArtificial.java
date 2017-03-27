package com.spoom.entity.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="order_artificial")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class OrderArtificial {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/*@ManyToOne
	@JoinColumn(name="order_id")
	private OrderLb orderLb;
	@ManyToOne
	@JoinColumn(name="artificial_id")
	private ArtificialFee artificialFee;*/
	private Integer orderId;
	private Integer artificialFeeId;
	private Integer dictionaryClassifyId;
	private String dictionaryClassifyName;
	private String name;
	private String style;
	private String standard;
	private BigDecimal price;
	private BigDecimal lowFee;
	private String remark;
	private Integer count;//数量
	private BigDecimal total;//总额  = 数量 x 定义人工单价
	private BigDecimal totalReal;//手动填写总额
	private Integer difCount;//决算变更数量
	private BigDecimal difTotal;//决算变更额度
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Integer status;
	@Version
	private Integer version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getArtificialFeeId() {
		return artificialFeeId;
	}
	public void setArtificialFeeId(Integer artificialFeeId) {
		this.artificialFeeId = artificialFeeId;
	}
	public Integer getDictionaryClassifyId() {
		return dictionaryClassifyId;
	}
	public void setDictionaryClassifyId(Integer dictionaryClassifyId) {
		this.dictionaryClassifyId = dictionaryClassifyId;
	}
	public String getDictionaryClassifyName() {
		return dictionaryClassifyName;
	}
	public void setDictionaryClassifyName(String dictionaryClassifyName) {
		this.dictionaryClassifyName = dictionaryClassifyName;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getLowFee() {
		return lowFee;
	}
	public void setLowFee(BigDecimal lowFee) {
		this.lowFee = lowFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getDifCount() {
		return difCount;
	}
	public void setDifCount(Integer difCount) {
		this.difCount = difCount;
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
	public BigDecimal getDifTotal() {
		return difTotal;
	}
	public void setDifTotal(BigDecimal difTotal) {
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
