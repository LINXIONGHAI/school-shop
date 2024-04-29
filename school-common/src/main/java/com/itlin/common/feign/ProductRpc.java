package com.itlin.common.feign;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2024-04-29 00:02:57
 */
@Data
public class ProductRpc implements Serializable {
    private static final long serialVersionUID = -85974816180289285L;
    
    private Object id;
    /**
     * 标题
     */
    private String title;
    /**
     * 封面图
     */
    private String coverImg;
    /**
     * 详情
     */
    private String detail;
    /**
     * 老价格
     */
    private Double oldPrice;
    /**
     * 新价格
     */
    private Double price;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 锁定库存
     */
    private Integer lockStock;


}

