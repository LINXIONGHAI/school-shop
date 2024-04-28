package com.itlin.product.entity;

import java.io.Serializable;

/**
 * (Banner)实体类
 *
 * @author makejava
 * @since 2024-04-29 00:02:56
 */
public class Banner implements Serializable {
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}

