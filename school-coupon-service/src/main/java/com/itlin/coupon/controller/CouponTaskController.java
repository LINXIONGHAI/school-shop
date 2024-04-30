package com.itlin.coupon.controller;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.emun.CouponCategoryEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.JsonData;
import com.itlin.coupon.service.CouponTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/couponTask/v1")
@Slf4j
public class CouponTaskController {

    @Resource
    private CouponTaskService taskService;

    /**
     * 锁定库存
     * @param
     * @return
     */
    @GetMapping("lockCoupon")
    public JsonData lockCoupon(String couponId,String orderId){
        try {
            return taskService.lockCoupon(couponId,orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(BizCodeEnum.RPC_ERRO);
        }

    }
}
