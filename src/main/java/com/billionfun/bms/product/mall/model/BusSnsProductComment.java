package com.billionfun.bms.product.mall.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusSnsProductComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_sns_product_comment"
    ,catalog="billionfun"
)

public class BusSnsProductComment  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
	@Column
     private String name;
	@Column
     private String content;
     @Column(name="user_id", length=45)
     private String userId;
     @Column(name="product_id", length=45)
     private String productId;
     @Column(name="goods_id", length=45)
     private String goodsId;
     @Column(name="product_group_id")
     private Long productGroupId;
     @Column(name="create_date", length=19)
     private Date createDate;
     @Column
     private String status;


    // Constructors

    /** default constructor */
    public BusSnsProductComment() {
    }

	/** minimal constructor */
    public BusSnsProductComment(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusSnsProductComment(Long id, String name, String content, String userId, String productId, String goodsId, Long productGroupId, Date createDate, String status) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.userId = userId;
        this.productId = productId;
        this.goodsId = goodsId;
        this.productGroupId = productGroupId;
        this.createDate = createDate;
        this.status = status;
    }

   
    // Property accessors
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getProductId() {
        return this.productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   
}