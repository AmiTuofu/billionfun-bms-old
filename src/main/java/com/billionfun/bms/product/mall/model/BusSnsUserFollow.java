package com.billionfun.bms.product.mall.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusSnsUserFollow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_sns_user_follow"
    ,catalog="billionfun"
)

public class BusSnsUserFollow  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
	@Column(name="create_date", length=19)
     private Timestamp createDate;
	@Column(name="follow_id", length=45)
     private String followId;
	@Column(name="user_id", length=45)
     private String userId;
     private Integer status;


    // Constructors

    /** default constructor */
    public BusSnsUserFollow() {
    }

	/** minimal constructor */
    public BusSnsUserFollow(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusSnsUserFollow(Long id, Timestamp createDate, String followId, String userId, Integer status) {
        this.id = id;
        this.createDate = createDate;
        this.followId = followId;
        this.userId = userId;
        this.status = status;
    }

   
    // Property accessors
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Timestamp getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    public String getFollowId() {
        return this.followId;
    }
    
    public void setFollowId(String followId) {
        this.followId = followId;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}