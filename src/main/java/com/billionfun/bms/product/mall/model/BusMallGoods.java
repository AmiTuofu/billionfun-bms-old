package com.billionfun.bms.product.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusMallGoods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bus_mall_goods", catalog = "billionfun")
public class BusMallGoods implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@Column(name = "product_id", length = 45)
	private String productId;
	@Column
	private Double price;
	@Column
	private Integer stock;
	@Column
	private String status;
	@Column(name = "create_date", length = 19)
	private Date createDate;
	@Column(name = "last_modify_date", length = 19)
	private String lastModifyDate;
	@Column(name = "user_id", length = 45)
	private String userId;

	// Constructors

	/** default constructor */
	public BusMallGoods() {
	}

	/** minimal constructor */
	public BusMallGoods(String id) {
		this.id = id;
	}

	/** full constructor */
	public BusMallGoods(String id, String productId, Double price,
			Integer stock, String status, Date createDate,
			String lastModifyDate, String userId) {
		this.id = id;
		this.productId = productId;
		this.price = price;
		this.stock = stock;
		this.status = status;
		this.createDate = createDate;
		this.lastModifyDate = lastModifyDate;
		this.userId = userId;
	}

	// Property accessors
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
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

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}