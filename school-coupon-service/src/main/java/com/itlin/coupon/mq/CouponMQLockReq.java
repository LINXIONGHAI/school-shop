package com.itlin.coupon.mq;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponMQLockReq implements Serializable {


    private String messageId;

    /**
     * 订单id
     */
    private String outTranceId;

    /**
     * 库存id
     */
    private String taskId;

    private String userId;


}
