package com.itlin.coupon.feign;

import com.itlin.common.util.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("school-order-service")
public interface ProduceOrederFeignRpc {

    @GetMapping("/api/order/v1/getByOutTraneId/{outTraneId}")
    public JsonData getByOutTrane(@PathVariable("outTraneId") String outTraneId);

}
