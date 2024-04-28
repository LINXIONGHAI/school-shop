package com.itlin.coupon.controller;

import com.itlin.common.util.JsonData;
import com.itlin.coupon.convert.CouponDtoConvert;
import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import com.itlin.coupon.service.CouponRecordService;
import com.itlin.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/couponRecord/v1")
@Slf4j
public class CouponRecordController {

    @Resource
    private CouponRecordService couponRecordService;


    /**
     * 分页查询优惠卷
     * @param
     * @return
     */
    @GetMapping("save")
    public JsonData save(String couponId){
        couponRecordService.save(couponId);

        return JsonData.buildSuccess();

    }



}
