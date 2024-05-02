package com.itlin.product.mq;

import lombok.Data;

@Data
public class StockLockMqDto {

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
