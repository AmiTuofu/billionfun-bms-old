package com.billionfun.bms.product.mall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusMallProductGroupAttrValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_mall_product_group_attr_value"
    ,catalog="billionfun"
)

public class BusMallProductGroupAttrValue  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
	 @Column(name="product_group_id")
     private Long productGroupId;
	 @Column(name="attr_id")
     private Long attrId;
	 @Column(name="attr_value_id")
     private Long attrValueId;


    // Constructors

    /** default constructor */
    public BusMallProductGroupAttrValue() {
    }

	/** minimal constructor */
    public BusMallProductGroupAttrValue(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusMallProductGroupAttrValue(Long id, Long productGroupId, Long attrId, Long attrValueId) {
        this.id = id;
        this.productGroupId = productGroupId;
        this.attrId = attrId;
        this.attrValueId = attrValueId;
    }

   
    // Property accessors
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
   

    public Long getProductGroupId() {
        return this.productGroupId;
    }
    
    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }
    
    

    public Long getAttrId() {
        return this.attrId;
    }
    
    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }
    
   

    public Long getAttrValueId() {
        return this.attrValueId;
    }
    
    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }
   








}