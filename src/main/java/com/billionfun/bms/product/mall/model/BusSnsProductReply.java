package com.billionfun.bms.product.mall.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusSnsProductReply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_sns_product_reply"
    ,catalog="billionfun"
)

public class BusSnsProductReply  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
	@Column(name="reply_id")
     private Long replyId;
	@Column
     private String name;
	@Column
     private String content;
     @Column(name="comment_id")
     private Long commentId;
     @Column(name="product_id", length=45)
     private String productId;
     @Column(name="goods_id", length=45)
     private String goodsId;
     @Column(name="product_group_id")
     private Long productGroupId;
     @Column(name="create_date", length=19)
     private Date createDate;
     @Column
     private Integer status;


    // Constructors

    /** default constructor */
    public BusSnsProductReply() {
    }

	/** minimal constructor */
    public BusSnsProductReply(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusSnsProductReply(Long id, Long replyId, String name, String content, Long commentId, String productId, String goodsId, Long productGroupId, Date createDate, Integer status) {
        this.id = id;
        this.replyId = replyId;
        this.name = name;
        this.content = content;
        this.commentId = commentId;
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
    public Long getReplyId() {
        return this.replyId;
    }
    
    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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
    
    public Long getCommentId() {
        return this.commentId;
    }
    
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}