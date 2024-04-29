package com.itlin.order.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ProductOrder)实体类
 *
 * @author makejava
 * @since 2024-04-29 19:37:36
 */
public class ProductOrder implements Serializable {
    private static final long serialVersionUID = -34799380399621287L;
    
    private Long id;
    /**
     * 订单唯一标识
     */
    private String outTradeNo;
    /**
     * NEW 未支付订单,PAY已经支付订单,CANCEL超时取消订单
     */
    private String state;
    /**
     * 订单生成时间
     */
    private Date createTime;
    /**
     * 订单总金额
     */
    private Double totalAmount;
    /**
     * 订单实际支付价格
     */
    private Double payAmount;
    /**
     * 支付类型，微信-银行-支付宝
     */
    private String payType;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 0表示未删除，1表示已经删除
     */
    private Integer del;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 订单类型 DAILY普通单，PROMOTION促销订单
     */
    private String orderType;
    /**
     * 收货地址 json存储
     */
    private String receiverAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

}

