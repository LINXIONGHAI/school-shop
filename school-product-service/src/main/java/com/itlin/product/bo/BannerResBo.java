package com.itlin.product.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * (Banner)实体类
 *
 * @author makejava
 * @since 2024-04-29 00:02:56
 */
@Data
public class BannerResBo implements Serializable {
    private static final long serialVersionUID = 362004223226578747L;
    
    private Integer id;
    /**
     * 图片
     */
    private String img;
    /**
     * 跳转地址
     */
    private String url;
    /**
     * 权重
     */
    private Integer weight;



}

