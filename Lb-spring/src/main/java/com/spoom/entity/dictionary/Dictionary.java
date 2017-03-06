package com.spoom.entity.dictionary;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author songnail
 * 字典表父级表
 *
 */
@Entity
@Table(name="dictionary")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cacheable
public class Dictionary implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int status;
	@Version
	private Integer version;
	//@OneToMany(fetch=FetchType.EAGER ,cascade = {CascadeType.ALL},mappedBy = "dictionary") 
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "dictionary") 
	@JsonIgnore
	private List<DictionaryClassify> dictionaryClassifys;
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
	public List<DictionaryClassify> getDictionaryClassifys() {
		return dictionaryClassifys;
	}
	public void setDictionaryClassifys(List<DictionaryClassify> dictionaryClassifys) {
		this.dictionaryClassifys = dictionaryClassifys;
	}
}
