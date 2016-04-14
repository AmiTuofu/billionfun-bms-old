package com.billionfun.bms.product.mall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusMallOrderItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bus_mall_order_item"
    ,catalog="billionfun"
)

public class BusMallOrderItem  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Integer id;
	@Column(name="order_id", length=45)
     private String orderId;
	@Column(name="goods_id", length=45)
     private String goodsId;
	@Column
     private Integer quantity;
     @Column(name="total_price", precision=22, scale=0)
     private Double totalPrice;


    // Constructors

    /** default constructor */
    public BusMallOrderItem() {
    }

	/** minimal constructor */
    public BusMallOrderItem(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public BusMallOrderItem(Integer id, String orderId, String goodsId, Integer quantity, Double totalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

   
    // Property accessors
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public String getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Double getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
   
}