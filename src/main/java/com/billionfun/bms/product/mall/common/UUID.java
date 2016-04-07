package com.billionfun.bms.product.mall.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class UUID {
	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	protected String id;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

}
