package com.billionfun.bms.product.mall.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusMallProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_mall_product"
    ,catalog="billionfun"
)

public class BusMallProduct  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private String id;
	@Column
     private String name;
     @Column(name="user_id", length=45)
     private String userId;
     @Column(name="product_group_id")
     private Long productGroupId;
     @Column(name="create_date", length=19)
     private Date createDate;
     @Column
     private Integer status;


    // Constructors

    /** default constructor */
    public BusMallProduct() {
    }

	/** minimal constructor */
    public BusMallProduct(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusMallProduct(String id, String name, String userId, Long productGroupId, Date createDate, Integer status) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.productGroupId = productGroupId;
        this.createDate = createDate;
        this.status = status;
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
    
    

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    

    public Long getProductGroupId() {
        return this.productGroupId;
    }
    
    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }
    
   

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}