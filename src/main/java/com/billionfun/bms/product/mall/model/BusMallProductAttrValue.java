package com.billionfun.bms.product.mall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusMallProductAttrValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_mall_product_attr_value"
    ,catalog="billionfun"
)

public class BusMallProductAttrValue  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
	@Column(name="product_id", length=45)
     private String productId;
	 @Column(name="attr_id")
     private Long attrId;
	 @Column(name="attr_value_id")
     private Long attrValueId;


    // Constructors

    /** default constructor */
    public BusMallProductAttrValue() {
    }

	/** minimal constructor */
    public BusMallProductAttrValue(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusMallProductAttrValue(Long id, String productId, Long attrId, Long attrValueId) {
        this.id = id;
        this.productId = productId;
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
    
    public String getProductId() {
        return this.productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
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