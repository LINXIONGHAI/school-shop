package com.itlin.product.service.impl;

import com.google.gson.Gson;
import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.JsonData;
import com.itlin.product.bo.CartBo;
import com.itlin.common.entity.CartItemVo;
import com.itlin.product.dto.CartSaveDto;
import com.itlin.product.entity.Product;
import com.itlin.product.service.CartService;
import com.itlin.product.service.ProductService;
import com.itlin.redis.util.RedisUtil;
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
        CartBo cartBo = new CartBo();
        List<CartItemVo> list = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            CartItemVo cartItemVo = gson.fromJson(o.toString(), CartItemVo.class);
            list.add(cartItemVo);
        }
        cartBo.setCartItemVoList(list);
        cartBo.setAmount();
        cartBo.setAmountSum();
        cartBo.setTotal();

        return JsonData.buildSuccess(cartBo);

    }

    @Override
    public JsonData del(String productId) {
        BoundHashOperations<String, Object, Object> mycats = redisUtil.getBoundHashOperations(myCat());
        Object redisProduct = mycats.get(productId);
        if (redisProduct != null) {
            mycats.delete(productId);
            return JsonData.buildSuccess();
        }
        throw new BizException(BizCodeEnum.CART_NO_PRODUCT);
    }


    @Override
    public JsonData changeCart(String productId, String productNum) {
        BoundHashOperations<String, Object, Object> boundHashOperations = redisUtil.getBoundHashOperations(myCat());
        List<Object> list = boundHashOperations.values();
        CartItemVo cartItemVo = new CartItemVo();
        Gson gson = new Gson();
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            CartItemVo cartItemVo1 = gson.fromJson(o.toString(), CartItemVo.class);
            if (cartItemVo1.getProduct() == Integer.parseInt(productId)) {
                cartItemVo = cartItemVo1;
            }
        }
        cartItemVo.setProductNum(Integer.parseInt(productNum));
        cartItemVo.setProductTilalAmount();
        boundHashOperations.put(productId, gson.toJson(cartItemVo));
        return JsonData.buildSuccess();

    }


    /**
     * 获取购物车key
     */
    public String myCat() {
        LoginUser loginUser = LoginThreadLocal.get();
        return redisUtil.buildKey("cart", "shop", String.valueOf(loginUser.getId()));
    }

}
