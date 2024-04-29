package com.itlin.order.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ProductOrderItem)实体类
 *
 * @author makejava
 * @since 2024-04-29 19:37:38
 */
public class ProductOrderItem implements Serializable {
    private static final long serialVersionUID = 415269124050416532L;
    
    private Object id;
    /**
     * 订单号
     */
    private Long productOrderId;
    
    private String outTradeNo;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String productImg;
    /**
     * 购买数量
     */
    private Integer buyNum;
    
    private Date createTime;
    /**
     * 购物项商品总价格
     */
    private Double totalAmount;
    /**
     * 购物项商品单价
     */
    private Object amount;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

}

