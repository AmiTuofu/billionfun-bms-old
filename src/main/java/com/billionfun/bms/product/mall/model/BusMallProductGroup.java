package com.billionfun.bms.product.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusMallProductGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_mall_product_group"
    ,catalog="billionfun"
)

public class BusMallProductGroup  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
	@Column 
	private String name;
     @Column
     private String description;
     @Column(name="category_id")
     private Long categoryId;
     @Column(name="user_id", length=45)
     private String userId;
     @Column(name="create_date", length=19)
     private Date createDate;
     @Column
     private String status;


    // Constructors

    /** default constructor */
    public BusMallProductGroup() {
    }

	/** minimal constructor */
    public BusMallProductGroup(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusMallProductGroup(Long id, String name, String description, Long categoryId, String userId, Date createDate, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.userId = userId;
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
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public Long getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    
   

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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