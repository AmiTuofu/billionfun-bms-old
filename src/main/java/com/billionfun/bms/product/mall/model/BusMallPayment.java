package com.billionfun.bms.product.mall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusMallPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bus_mall_payment", catalog = "billionfun")
public class BusMallPayment implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "total_price", precision = 22, scale = 0)
	private Double totalPrice;

	// Constructors

	/** default constructor */
	public BusMallPayment() {
	}

	/** minimal constructor */
	public BusMallPayment(Long id) {
		this.id = id;
	}

	/** full constructor */
	public BusMallPayment(Long id, Double totalPrice) {
		this.id = id;
		this.totalPrice = totalPrice;
	}

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}