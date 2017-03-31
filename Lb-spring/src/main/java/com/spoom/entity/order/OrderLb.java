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
	private String depositType;
	private Double budget;
	//-----------------------
	private Integer buildDays;//施工天数
	private Integer buildPeople;//施工人数
	private Date beginBuildDate;//开始施工日期
	private Date doneBuildDate;//结束施工日期
	private Double partMoney;//已收金额
	private String sets;//是否选择套餐
	//-----------------------
	
	/*@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8") 
	private Date signingDate;
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8") 
	private Date expectedDate;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Integer status;
	private String remark;
	@Version
	private Integer version;
	
	private Double mainPrice;//主材费
	private Double latexPrice;
	private Double artPrice;
	private Double woodWaterPrice;
	private Double artificialPrice;
	
	private Double mainPriceReal;//主材预算费
	private Double latexPriceReal;
	private Double artPriceReal;
	private Double woodWaterPriceReal;
	
	private Double assistPrice;
	private Double assistPriceReal;//辅材预算费
	
	private Double artificialPriceReal;//人工预算费
	
	private Double difManTotal;
	private Double difMaterialTotal;
	private Double grossMan;
	private Double grossManFinal;
	private Double grossMaterial;
	private Double grossMaterialFinal;
	private Double gross;
	private Double grossFinal;
	
	private String orderStatus;
	private String orderFrom;
	
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
	public String getDepositType() {
		return depositType;
	}
	public void setDepositType(String depositType) {
		this.depositType = depositType;
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
	public Double getDifManTotal() {
		return difManTotal;
	}
	public void setDifManTotal(Double difManTotal) {
		this.difManTotal = difManTotal;
	}
	public Double getDifMaterialTotal() {
		return difMaterialTotal;
	}
	public void setDifMaterialTotal(Double difMaterialTotal) {
		this.difMaterialTotal = difMaterialTotal;
	}
	public Double getGrossMan() {
		return grossMan;
	}
	public void setGrossMan(Double grossMan) {
		this.grossMan = grossMan;
	}
	public Double getGrossManFinal() {
		return grossManFinal;
	}
	public void setGrossManFinal(Double grossManFinal) {
		this.grossManFinal = grossManFinal;
	}
	public Double getGrossMaterial() {
		return grossMaterial;
	}
	public void setGrossMaterial(Double grossMaterial) {
		this.grossMaterial = grossMaterial;
	}
	public Double getGrossMaterialFinal() {
		return grossMaterialFinal;
	}
	public void setGrossMaterialFinal(Double grossMaterialFinal) {
		this.grossMaterialFinal = grossMaterialFinal;
	}
	public Double getGrossFinal() {
		return grossFinal;
	}
	public void setGrossFinal(Double grossFinal) {
		this.grossFinal = grossFinal;
	}
	public Integer getBuildDays() {
		return buildDays;
	}
	public void setBuildDays(Integer buildDays) {
		this.buildDays = buildDays;
	}
	public Integer getBuildPeople() {
		return buildPeople;
	}
	public void setBuildPeople(Integer buildPeople) {
		this.buildPeople = buildPeople;
	}
	public Date getBeginBuildDate() {
		return beginBuildDate;
	}
	public void setBeginBuildDate(Date beginBuildDate) {
		this.beginBuildDate = beginBuildDate;
	}
	public Date getDoneBuildDate() {
		return doneBuildDate;
	}
	public void setDoneBuildDate(Date doneBuildDate) {
		this.doneBuildDate = doneBuildDate;
	}
	public Double getPartMoney() {
		return partMoney;
	}
	public void setPartMoney(Double partMoney) {
		this.partMoney = partMoney;
	}
	public String getSets() {
		return sets;
	}
	public void setSets(String sets) {
		this.sets = sets;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderFrom() {
		return orderFrom;
	}
	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}
}
