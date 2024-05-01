package com.itlin.common.feign.dto;

import com.itlin.common.entity.CartItemVo;
import com.itlin.common.feign.ProductRpc;
import lombok.Data;

import java.util.List;

@Data
public class ProductLockStockDto {

    List<CartItemVo> cartItemVoList;
    String outTradeNo;

}
