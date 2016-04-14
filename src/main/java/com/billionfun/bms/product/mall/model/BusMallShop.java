package com.billionfun.bms.product.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusMallShop entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bus_mall_shop", catalog = "billionfun")
public class BusMallShop implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@Column
	private String name;
	@Column
	private String province;
	@Column
	private String city;
	@Column
	private String district;
	@Column(name = "real_name", length = 45)
	private String realName;
	@Column
	private String mobile;
	@Column
	private String status;
	@Column(name = "create_date", length = 19)
	private Date createDate;
	@Column(name = "user_id", length = 45)
	private String userId;

	// Constructors

	/** default constructor */
	public BusMallShop() {
	}

	/** minimal constructor */
	public BusMallShop(String id) {
		this.id = id;
	}

	/** full constructor */
	public BusMallShop(String id, String name, String province, String city,
			String district, String realName, String mobile, String status,
			Date createDate, String userId) {
		this.id = id;
		this.name = name;
		this.province = province;
		this.city = city;
		this.district = district;
		this.realName = realName;
		this.mobile = mobile;
		this.status = status;
		this.createDate = createDate;
		this.userId = userId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}