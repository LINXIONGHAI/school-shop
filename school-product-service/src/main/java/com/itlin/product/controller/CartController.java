package com.itlin.product.controller;

import com.itlin.common.util.JsonData;
import com.itlin.product.bo.ProductReqBo;
import com.itlin.product.bo.ProductResBo;
import com.itlin.product.convert.ProductBoToDto;
import com.itlin.product.convert.ProductDtoToBo;
import com.itlin.product.dto.CartSaveDto;
import com.itlin.product.dto.ProductReqDto;
import com.itlin.product.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart/v1")
public class CartController {

    @Resource
    private CartService cartService;

    @ApiOperation("添加购物车")
    @PostMapping("add")
    public JsonData add(@RequestBody CartSaveDto cartSaveDto) {

        return cartService.add(cartSaveDto);

    }

    /**
     * 获取我的购物车列表
     */
    @ApiOperation("获取我的购物车列表")
    @GetMapping("/getCartList")
    public JsonData getCartList(){
        return cartService.getCartList();

    }

    /**
     * 删除购物车
     */
    @ApiOperation("删除购物车")
    @GetMapping("/del/{product_id}")
    public JsonData del(@PathVariable String product_id){
        return cartService.del(product_id);

    }

    /**
     * 修改商品数量
     */
    @ApiOperation("修改商品数量")
    @PostMapping("/changeCart")
    public JsonData changeCart(String productId,String productNum) {

        return cartService.changeCart(productId,productNum);

    }


}
