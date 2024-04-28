package com.itlin.coupon.dto;

import com.itlin.common.util.PageUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Coupon)实体类
 *
 * @author makejava
 * @since 2024-04-28 20:25:04
 */
@Data
public class CouponDto extends PageUtils implements Serializable  {
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


}

