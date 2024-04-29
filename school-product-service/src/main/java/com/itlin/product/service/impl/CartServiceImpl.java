package com.itlin.product.service.impl;

import com.google.gson.Gson;
import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.JsonData;
import com.itlin.product.bo.CartBo;
import com.itlin.product.bo.CartItemVo;
import com.itlin.product.dto.CartSaveDto;
import com.itlin.product.entity.Product;
import com.itlin.product.service.CartService;
import com.itlin.product.service.ProductService;
import com.itlin.redis.util.RedisUtil;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private ProductService productService;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public JsonData add(CartSaveDto cartSaveDto) {
        Integer productId = cartSaveDto.getProductId();
        Integer productNum = cartSaveDto.getProductNum();
        String productIdStr = String.valueOf(productId);
        Product product = productService.queryById(productId);
        if (product == null) {
            throw new BizException(BizCodeEnum.PRODUCT_NO_NULL);
        }
        //String: 商品ID
        BoundHashOperations<String, Object, Object> boundHashOperations = redisUtil.getBoundHashOperations(myCat());

        Object json = boundHashOperations.get(productIdStr);

        Gson gson = new Gson();
        if (json == null) {
            //购物陈没有这个商品
            CartItemVo cartItemVo = new CartItemVo();
            cartItemVo.setProductNum(productNum);
            cartItemVo.setProduct(productId);
            cartItemVo.setProductAmout(new BigDecimal(product.getPrice()));
            cartItemVo.setProductTitle(product.getTitle());
            cartItemVo.setProductImage(product.getCoverImg());
            cartItemVo.setProductTilalAmount();
            boundHashOperations.put(productIdStr, gson.toJson(cartItemVo));

        } else {
            //有商品
            String str = String.valueOf(json);
            CartItemVo cartItemVo = gson.fromJson(str, CartItemVo.class);
            cartItemVo.setProductNum(cartItemVo.getProductNum() + productNum);
            cartItemVo.setProductTilalAmount();
            boundHashOperations.put(productIdStr, gson.toJson(cartItemVo));

        }
        return JsonData.buildSuccess();
    }

    @Override
    public JsonData getCartList() {
        BoundHashOperations<String, Object, Object> myCatAll = redisUtil.getBoundHashOperations(myCat());
        if (myCatAll == null) {
            return null;
        }
        List<Object> items = myCatAll.values();
        Gson gson = new Gson();
        CartBo cartBo=new CartBo();
        List<CartItemVo> list=new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            CartItemVo cartItemVo=gson.fromJson(o.toString(),CartItemVo.class);
            list.add(cartItemVo);
        }
        cartBo.setCartItemVoList(list);
        cartBo.setAmount();
        cartBo.setAmountSum();
        cartBo.setTotal();

        return JsonData.buildSuccess(cartBo);

    }


    /**
     * 获取购物车key
     */
    public String myCat() {
        LoginUser loginUser = LoginThreadLocal.get();
        return redisUtil.buildKey("cart", "shop", String.valueOf(loginUser.getId()));
    }

}
