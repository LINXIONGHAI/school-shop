package com.itlin.product.controller;

import com.itlin.common.entity.CartItemVo;
import com.itlin.common.feign.ProductRpc;
import com.itlin.common.feign.dto.ProduceRpcReqDto;
import com.itlin.common.feign.dto.ProductLockStockDto;
import com.itlin.common.util.JsonData;
import com.itlin.product.bo.ProductReqBo;
import com.itlin.product.bo.ProductResBo;
import com.itlin.product.convert.ProductBoToDto;
import com.itlin.product.convert.ProductDtoToBo;
import com.itlin.product.dto.ProductReqDto;
import com.itlin.product.service.ProductService;
import com.itlin.product.service.ProductTaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/productTask/v1")
public class ProductTaskController {

    @Resource
    private ProductTaskService productTaskService;


    @ApiOperation("RPC库存锁定")
    @PostMapping("/lockStock")
    public JsonData lockStock(@RequestBody ProductLockStockDto productLockStockDto) {
        return productTaskService.lockStock(productLockStockDto.getCartItemVoList(),productLockStockDto.getOutTradeNo());
    }


}
