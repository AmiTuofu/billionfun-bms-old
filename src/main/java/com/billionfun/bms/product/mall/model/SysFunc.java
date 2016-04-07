package com.billionfun.bms.product.mall.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the sys_func database table.
 * 
 */
@Entity
@Table(name="sys_func")
@NamedQuery(name="SysFunc.findAll", query="SELECT s FROM SysFunc s")
public class SysFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="img_url")
	private String imgUrl;

	private Integer level;

	private String name;

	@Column(name="parent_id")
	private String parentId;

	private Integer status;

	private Integer type;

	private String url;
	
	@Column(name="style_class")
	private String styleClass;
	
	@Column(name="is_leaf")
	private boolean isLeaf;
	
	private boolean expanded;

	@JsonIgnoreProperties
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(mappedBy = "listFuncs",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	private List<SysRole> listRoles;
	
	@JsonIgnoreProperties
	@Transient
	private List<SysFunc> listFuncs;
	
	public SysFunc() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SysRole> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<SysRole> listRoles) {
		this.listRoles = listRoles;
	}

	public List<SysFunc> getListFuncs() {
		return listFuncs;
	}

	public void setListFuncs(List<SysFunc> listFuncs) {
		this.listFuncs = listFuncs;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
}