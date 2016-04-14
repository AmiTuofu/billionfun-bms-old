package com.billionfun.bms.product.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusMallDelivery entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_mall_delivery"
    ,catalog="billionfun"
)

public class BusMallDelivery  implements java.io.Serializable {


    // Fields    
	 @Id 
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
	 @Column
     private String province;
	 @Column
     private String city;
	 @Column
     private String distinct;
	 @Column
	 private String address;
	 @Column
	 private String consignee;
	 @Column
	 private String mobile;
     @Column(name="zip_code", length=45)
     private String zipCode;
     private String status;
     @Column(name="is_default", length=4)
     private String isDefault;
     @Column(name="create_date", length=19)
     private Date createDate;


    // Constructors

    /** default constructor */
    public BusMallDelivery() {
    }

	/** minimal constructor */
    public BusMallDelivery(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusMallDelivery(Long id, String province, String city, String distinct, String address, String consignee, String mobile, String zipCode, String status, String isDefault, Date createDate) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.distinct = distinct;
        this.address = address;
        this.consignee = consignee;
        this.mobile = mobile;
        this.zipCode = zipCode;
        this.status = status;
        this.isDefault = isDefault;
        this.createDate = createDate;
    }
    // Property accessors
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public String getDistinct() {
        return this.distinct;
    }
    
    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getConsignee() {
        return this.consignee;
    }
    
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getZipCode() {
        return this.zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDefault() {
        return this.isDefault;
    }
    
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
   








}