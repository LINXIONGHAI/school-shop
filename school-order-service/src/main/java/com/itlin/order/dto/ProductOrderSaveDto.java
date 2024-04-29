package com.itlin.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (ProductOrder)实体类
 *
 * @author makejava
 * @since 2024-04-29 19:37:36
 */
@Data
public class ProductOrderSaveDto implements Serializable {
    private static final long serialVersionUID = -34799380399621287L;

    private List<Integer> prductIds;

    private Integer couponId;

    private Integer payType;

    private Integer clientType;




}

