package com.spoom.entity.material;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spoom.entity.dictionary.DictionaryClassify;

@Entity
@Table(name="material_classify")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class MaterialClassify {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="dictionary_classify_id")
	private DictionaryClassify dictionaryClassify;
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "materialClassify")
	@JsonIgnore
	private List<MaterialProduct> materialProducts;
	private String name;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int status;
	@Version
	private Integer version;
	private Integer place;
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
	public List<MaterialProduct> getMaterialProducts() {
		return materialProducts;
	}
	public void setMaterialProducts(List<MaterialProduct> materialProducts) {
		this.materialProducts = materialProducts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getPlace() {
		return place;
	}
	public void setPlace(Integer place) {
		this.place = place;
	}
}
