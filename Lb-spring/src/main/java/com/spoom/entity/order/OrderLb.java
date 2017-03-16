package com.spoom.entity.order;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="order_lb")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class OrderLb {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String orderNo;
	private String contractNo;
	private String orderTm;
	private String sysNo;
	private String customer;
	private String customerCommissioner;
	private String tel;
	private String buildAddress;
	private String buildCaptain;
	private String buildCaptainPhone;
	private String uptownName;
	private String region;
	private String brushArea;
	private Double deposit;
	private Double budget;
	private Double gross;
	/*@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy/MM/dd HH:mm",timezone = "GMT+8") 
	private Date signingDate;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm",timezone = "GMT+8") 
	private Date expectedDate;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Integer status;
	@Lob
	private String remark;
	@Version
	private Integer version;
	
	private Double mainPrice;
	private Double assistPrice;
	private Double latexPrice;
	private Double artPrice;
	private Double woodWaterPrice;
	private Double artificialPrice;
	
	private Double mainPriceReal;
	private Double assistPriceReal;
	private Double latexPriceReal;
	private Double artPriceReal;
	private Double woodWaterPriceReal;
	private Double artificialPriceReal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getOrderTm() {
		return orderTm;
	}
	public void setOrderTm(String orderTm) {
		this.orderTm = orderTm;
	}
	public String getSysNo() {
		return sysNo;
	}
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustomerCommissioner() {
		return customerCommissioner;
	}
	public void setCustomerCommissioner(String customerCommissioner) {
		this.customerCommissioner = customerCommissioner;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBuildAddress() {
		return buildAddress;
	}
	public void setBuildAddress(String buildAddress) {
		this.buildAddress = buildAddress;
	}
	public String getBuildCaptain() {
		return buildCaptain;
	}
	public void setBuildCaptain(String buildCaptain) {
		this.buildCaptain = buildCaptain;
	}
	public String getBuildCaptainPhone() {
		return buildCaptainPhone;
	}
	public void setBuildCaptainPhone(String buildCaptainPhone) {
		this.buildCaptainPhone = buildCaptainPhone;
	}
	public String getUptownName() {
		return uptownName;
	}
	public void setUptownName(String uptownName) {
		this.uptownName = uptownName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getBrushArea() {
		return brushArea;
	}
	public void setBrushArea(String brushArea) {
		this.brushArea = brushArea;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public Double getGross() {
		return gross;
	}
	public void setGross(Double gross) {
		this.gross = gross;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Double getMainPrice() {
		return mainPrice;
	}
	public void setMainPrice(Double mainPrice) {
		this.mainPrice = mainPrice;
	}
	public Double getAssistPrice() {
		return assistPrice;
	}
	public void setAssistPrice(Double assistPrice) {
		this.assistPrice = assistPrice;
	}
	public Double getLatexPrice() {
		return latexPrice;
	}
	public void setLatexPrice(Double latexPrice) {
		this.latexPrice = latexPrice;
	}
	public Double getArtPrice() {
		return artPrice;
	}
	public void setArtPrice(Double artPrice) {
		this.artPrice = artPrice;
	}
	public Double getWoodWaterPrice() {
		return woodWaterPrice;
	}
	public void setWoodWaterPrice(Double woodWaterPrice) {
		this.woodWaterPrice = woodWaterPrice;
	}
	public Double getMainPriceReal() {
		return mainPriceReal;
	}
	public void setMainPriceReal(Double mainPriceReal) {
		this.mainPriceReal = mainPriceReal;
	}
	public Double getAssistPriceReal() {
		return assistPriceReal;
	}
	public void setAssistPriceReal(Double assistPriceReal) {
		this.assistPriceReal = assistPriceReal;
	}
	public Double getLatexPriceReal() {
		return latexPriceReal;
	}
	public void setLatexPriceReal(Double latexPriceReal) {
		this.latexPriceReal = latexPriceReal;
	}
	public Double getArtPriceReal() {
		return artPriceReal;
	}
	public void setArtPriceReal(Double artPriceReal) {
		this.artPriceReal = artPriceReal;
	}
	public Double getWoodWaterPriceReal() {
		return woodWaterPriceReal;
	}
	public void setWoodWaterPriceReal(Double woodWaterPriceReal) {
		this.woodWaterPriceReal = woodWaterPriceReal;
	}
	public Double getArtificialPrice() {
		return artificialPrice;
	}
	public void setArtificialPrice(Double artificialPrice) {
		this.artificialPrice = artificialPrice;
	}
	public Double getArtificialPriceReal() {
		return artificialPriceReal;
	}
	public void setArtificialPriceReal(Double artificialPriceReal) {
		this.artificialPriceReal = artificialPriceReal;
	}
}
