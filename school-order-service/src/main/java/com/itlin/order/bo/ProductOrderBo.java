package com.itlin.order.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (ProductOrder)实体类
 *
 * @author makejava
 * @since 2024-04-29 19:37:36
 */
@Data
public class ProductOrderBo implements Serializable {
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

}

