package com.billionfun.bms.product.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusMallOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bus_mall_order", catalog = "billionfun")
public class BusMallOrder implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@Column(name = "user_id", length = 45)
	private String userId;
	@Column(name = "total_price", precision = 22, scale = 0)
	private Double totalPrice;
	@Column(name = "freight_price", precision = 22, scale = 0)
	private Double freightPrice;
	@Column(name = "status", length = 4)
	private String status;
	@Column(name = "delivery_id")
	private Long deliveryId;
	@Column(name = "payment_id", length = 45)
	private String paymentId;
	@Column(name = "create_date", length = 19)
	private Date createDate;

	// Constructors

	/** default constructor */
	public BusMallOrder() {
	}

	/** minimal constructor */
	public BusMallOrder(String id) {
		this.id = id;
	}

	/** full constructor */
	public BusMallOrder(String id, String userId, Double totalPrice,
			Double freightPrice, String status, Long deliveryId,
			String paymentId) {
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.freightPrice = freightPrice;
		this.status = status;
		this.deliveryId = deliveryId;
		this.paymentId = paymentId;
	}

	// Property accessors
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getFreightPrice() {
		return this.freightPrice;
	}

	public void setFreightPrice(Double freightPrice) {
		this.freightPrice = freightPrice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDeliveryId() {
		return this.deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}