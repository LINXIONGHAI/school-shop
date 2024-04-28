package com.itlin.coupon.dto;

import com.itlin.common.util.PageUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (CouponRecord)实体类
 *
 * @author makejava
 * @since 2024-04-28 20:25:02
 */
@Data
public class CouponRecordDto extends PageUtils implements Serializable {
    private static final long serialVersionUID = -38964996659749669L;
    
    private Object id;
    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 使用状态  可用 NEW,已使用USED,过期 EXPIRED;
     */
    private String useState;


    /**
     * 优惠券标题
     */
    private String couponTitle;


    /**
     * 抵扣价格
     */
    private Double price;
    /**
     * 满多少才可以使用
     */
    private Double conditionPrice;

}

