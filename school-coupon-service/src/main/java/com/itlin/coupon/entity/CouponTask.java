package com.itlin.coupon.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (CouponTask)实体类
 *
 * @author makejava
 * @since 2024-04-30 14:42:50
 */
@Data
public class CouponTask implements Serializable {
    private static final long serialVersionUID = -94387353921540590L;
    
    private Long id;
    /**
     * 优惠券记录id
     */
    private Long couponRecordId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 订单号
     */
    private String outTradeNo;
    /**
     * 锁定状态 锁定LOCK-完成FINISH 取消CANCEL
     */
    private String lockState;


}

