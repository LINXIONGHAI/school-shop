package com.itlin.product.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ProductTask)实体类
 *
 * @author makejava
 * @since 2024-04-30 19:40:59
 */
@Data
public class ProductTask implements Serializable {
    private static final long serialVersionUID = 246372933598623299L;
    
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 购买数量
     */
    private Integer buyNum;
    /**
     * 商品标题
     */
    private String productName;
    /**
     * 锁定状态锁定LOCK  完成FINISH-取消CANCEL
     */
    private String lockState;
    
    private String outTradeNo;
    
    private Date createTime;


}

