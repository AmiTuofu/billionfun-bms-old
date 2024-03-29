package com.billionfun.bms.product.mall.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sys_data_dictionary database table.
 * 
 */
@Entity
@Table(name = "bus_mall_category_attr_value")
@NamedQuery(name = "BusMallCategoryAttrValue.findAll", query = "SELECT s FROM BusMallCategoryAttrValue s")
public class BusMallCategoryAttrValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column(name = "category_attr_id")
	private Long categoryAttrId;

	public BusMallCategoryAttrValue() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryAttrId() {
		return categoryAttrId;
	}

	public void setCategoryAttrId(Long categoryAttrId) {
		this.categoryAttrId = categoryAttrId;
	}

}