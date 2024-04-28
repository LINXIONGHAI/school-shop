package com.itlin.school.auth.feign;

import com.itlin.common.util.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "school-coupon-service")
public interface CouponFeignRpc {

    @GetMapping("/api/couponRecord/v1/new_user_coupon")
    public JsonData loginCoupon(@RequestParam("userId") String userId);
}
