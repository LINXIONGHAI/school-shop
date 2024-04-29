package com.itlin.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (ProductOrderItem)实体类
 *
 * @author makejava
 * @since 2024-04-29 19:38:43
 */
@Data
public class ProductOrderItemDto implements Serializable {
    private static final long serialVersionUID = -92951130662309337L;
    
    private Long id;
    /**
     * 订单号
     */
    private Long productOrderId;
    
    private String outTradeNo;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String productImg;
    /**
     * 购买数量
     */
    private Integer buyNum;
    
    private Date createTime;
    /**
     * 购物项商品总价格
     */
    private Double totalAmount;
    /**
     * 购物项商品单价
     */
    private BigDecimal amount;


}

