package com.itlin.product.dto;

import lombok.Data;

@Data
public class CartSaveDto {

    /**
     * 商品Id
     */
    private Integer productId;

    /**
     * 山坡购买的数量
     */
    private Integer productNum;


}
