package com.itlin.product.bo;

import java.math.BigDecimal;
import java.util.List;

public class CartBo {
    private List<CartItemVo> cartItemVoList;

    /**
     * 商品总件数
     */
    private Integer total;

    /**
     * 整个购物车总价
     */
    private BigDecimal amount;

    /**
     * 实际支付总价
     */
    private BigDecimal amountSum;


    public List<CartItemVo> getCartItemVoList() {
        return cartItemVoList;
    }

    public void setCartItemVoList(List<CartItemVo> cartItemVoList) {
        this.cartItemVoList = cartItemVoList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal() {
        this.total=cartItemVoList.stream().mapToInt(CartItemVo::getProductNum).sum();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount() {
        BigDecimal bigDecimal=new BigDecimal("0");
        if(cartItemVoList!=null){
            cartItemVoList.stream().forEach((item)->{
                bigDecimal.add(item.getProductTilalAmount());
            });
        }

        this.amount = bigDecimal;
    }

    public BigDecimal getAmountSum() {
        return amountSum;
    }

    public void setAmountSum() {
        BigDecimal bigDecimal=new BigDecimal("0");
        if(cartItemVoList!=null){
            cartItemVoList.stream().forEach((item)->{
                bigDecimal.add(item.getProductTilalAmount());
            });
        }

        this.amountSum = bigDecimal;
    }
}
