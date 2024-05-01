package com.itlin.product.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ProductTask)实体类
 *
 * @author makejava
 * @since 2024-04-30 19:40:59
 */
public class ProductTask implements Serializable {
    private static final long serialVersionUID = 246372933598623299L;
    
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 购买数量
     */
    private Integer buyNum;
    /**
     * 商品标题
     */
    private String productName;
    /**
     * 锁定状态锁定LOCK  完成FINISH-取消CANCEL
     */
    private String lockState;
    
    private String outTradeNo;
    
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

