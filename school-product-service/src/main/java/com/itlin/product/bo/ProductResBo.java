package com.itlin.product.bo;

import com.itlin.common.util.PageUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2024-04-29 00:02:57
 */
public class ProductResBo extends PageUtils implements Serializable {
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


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLockStock() {
        return lockStock;
    }

    public void setLockStock(Integer lockStock) {
        this.lockStock = lockStock;
    }

}

