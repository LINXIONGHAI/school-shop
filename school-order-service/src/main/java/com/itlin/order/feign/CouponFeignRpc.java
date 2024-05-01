package com.itlin.order.feign;

import com.itlin.common.util.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("school-coupon-service")
public interface CouponFeignRpc {

    @GetMapping("/api/couponTask/v1/lockCoupon")
    public JsonData lockCoupon(@RequestParam("couponId") String couponId, @RequestParam("orderId") String orderId);
}
