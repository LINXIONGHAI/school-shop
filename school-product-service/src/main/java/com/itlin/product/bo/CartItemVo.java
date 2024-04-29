package com.itlin.product.bo;

import lombok.Data;

import java.math.BigDecimal;


public class CartItemVo {
    /**
     * * 商品id
     * * 购买数量
     * * 商品标题（冗余）
     * * 商品图片（冗余）
     * * 商品单价
     * * 总价格 ( 单价*数量 )
     */

    /**
     * 商品Id
     */
    private Integer product;

    /**
     * 商品数量
     */
    private Integer productNum;

    /**
     * 商品标题
     */
    private String productTitle;

    /**
     * 商品图片
     */
    private String productImage;

    /**
     * 商品金额
     */
    private BigDecimal productAmout;

    /**
     * 单个商品项总金额
     */
    private BigDecimal productTilalAmount;


    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getProductAmout() {
        return productAmout;
    }

    public void setProductAmout(BigDecimal productAmout) {
        this.productAmout = productAmout;
    }

    public BigDecimal getProductTilalAmount() {
        return productTilalAmount;
    }

    public void setProductTilalAmount() {
        this.productTilalAmount= this.productTilalAmount.multiply(new BigDecimal(String.valueOf(this.productNum)));

    }
}
