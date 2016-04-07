package com.billionfun.bms.product.mall.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the sys_user database table.
 * 
 */
@Entity
@Table(name = "sys_user")
@NamedQuery(name = "SysUser.findAll", query = "SELECT s FROM SysUser s")
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String address;

	@Column(name = "city_code")
	private Integer cityCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "create_optr")
	private String createOptr;

	@Column(name = "dist_code")
	private Integer distCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "effect_end_date")
	private Date effectEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "effect_start_date")
	private Date effectStartDate;

	private String email;

	@Column(name = "full_name")
	private String fullName;

	private String mobile;

	@Column(name = "modify_optr")
	private String modifyOptr;

	private String password;

	@Column(name = "prov_code")
	private Integer provCode;

	private String remark;

	private Integer status;

	private String telephone;

	@Column(name = "user_type")
	private Integer userType;

	private String username;

	@JsonIgnoreProperties
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<SysRole> listRoles;

	@JsonIgnoreProperties
	@Transient
	private List<SysFunc> listFuncs;

	@JsonIgnoreProperties
	@Transient
	private List finishedEvents;

	@JsonIgnoreProperties
	@Transient
	private Integer unfinichedCount;

	public SysUser() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateOptr() {
		return this.createOptr;
	}

	public void setCreateOptr(String createOptr) {
		this.createOptr = createOptr;
	}

	public Integer getDistCode() {
		return this.distCode;
	}

	public void setDistCode(Integer distCode) {
		this.distCode = distCode;
	}

	public Date getEffectEndDate() {
		return this.effectEndDate;
	}

	public void setEffectEndDate(Date effectEndDate) {
		this.effectEndDate = effectEndDate;
	}

	public Date getEffectStartDate() {
		return this.effectStartDate;
	}

	public void setEffectStartDate(Date effectStartDate) {
		this.effectStartDate = effectStartDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getModifyOptr() {
		return this.modifyOptr;
	}

	public void setModifyOptr(String modifyOptr) {
		this.modifyOptr = modifyOptr;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getProvCode() {
		return this.provCode;
	}

	public void setProvCode(Integer provCode) {
		this.provCode = provCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List getFinishedEvents() {
		return finishedEvents;
	}

	public void setFinishedEvents(List finishedEvents) {
		this.finishedEvents = finishedEvents;
	}

	public Integer getUnfinichedCount() {
		return unfinichedCount;
	}

	public void setUnfinichedCount(Integer unfinichedCount) {
		this.unfinichedCount = unfinichedCount;
	}

}