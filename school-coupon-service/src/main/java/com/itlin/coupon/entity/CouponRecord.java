package com.itlin.coupon.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (CouponRecord)实体类
 *
 * @author makejava
 * @since 2024-04-28 20:25:02
 */
public class CouponRecord implements Serializable {
    private static final long serialVersionUID = -38964996659749669L;
    
    private Object id;
    /**
     * 优惠券id
     */
    private Long couponId;
    /**
     * 创建时间获得时间
     */
    private Date createTime;
    /**
     * 使用状态  可用 NEW,已使用USED,过期 EXPIRED;
     */
    private String useState;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 优惠券标题
     */
    private String couponTitle;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 抵扣价格
     */
    private Double price;
    /**
     * 满多少才可以使用
     */
    private Double conditionPrice;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUseState() {
        return useState;
    }

    public void setUseState(String useState) {
        this.useState = useState;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getConditionPrice() {
        return conditionPrice;
    }

    public void setConditionPrice(Double conditionPrice) {
        this.conditionPrice = conditionPrice;
    }

}

