package com.itlin.order.feign;

import com.itlin.common.feign.dto.ProduceRpcReqDto;
import com.itlin.common.feign.dto.ProductLockStockDto;
import com.itlin.common.util.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("school-product-service")
public interface ProductServiceRpc {



    @PostMapping("/api/product/v1/getListByIds")
    public JsonData getListByIds(@RequestBody ProduceRpcReqDto produceRpcReqDto);


    @PostMapping("/api/productTask/v1/lockStock")
    public JsonData lockStock(@RequestBody ProductLockStockDto productLockStockDto);
}
