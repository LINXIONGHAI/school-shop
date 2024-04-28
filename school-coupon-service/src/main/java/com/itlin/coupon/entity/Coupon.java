package com.itlin.coupon.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Coupon)实体类
 *
 * @author makejava
 * @since 2024-04-28 20:25:04
 */
public class Coupon implements Serializable {
    private static final long serialVersionUID = -45411786215207775L;
    /**
     * id
     */
    private Long id;
    /**
     * 优惠卷类型[NEW_USER注册赠券，TASK任务卷，PROMOTION促销劵]
     */
    private String category;
    /**
     * 发布状态, PUBLISH发布，DRAFT草稿，OFFLINE下线
     */
    private String publish;
    /**
     * 优惠券图片
     */
    private String couponImg;
    /**
     * 优惠券标题
     */
    private String couponTitle;
    /**
     * 抵扣价格
     */
    private Double price;
    /**
     * 每人限制张数
     */
    private Integer userLimit;
    /**
     * 优惠券开始有效时间
     */
    private Date startTime;
    /**
     * 优惠券失效时间
     */
    private Date endTime;
    /**
     * 优惠券总量
     */
    private Integer publishCount;
    /**
     * 库存
     */
    private Integer stock;
    
    private Date createTime;
    /**
     * 满多少才可以使用
     */
    private Double conditionPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getCouponImg() {
        return couponImg;
    }

    public void setCouponImg(String couponImg) {
        this.couponImg = couponImg;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
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

    public Integer getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getConditionPrice() {
        return conditionPrice;
    }

    public void setConditionPrice(Double conditionPrice) {
        this.conditionPrice = conditionPrice;
    }

}

