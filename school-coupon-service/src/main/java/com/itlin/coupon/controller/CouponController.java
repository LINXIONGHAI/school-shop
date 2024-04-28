package com.itlin.coupon.controller;

import com.itlin.coupon.convert.CouponDtoConvert;
import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import com.itlin.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/coupon/v1")
@Slf4j
public class CouponController {

    @Resource
    private CouponService couponService;


    /**
     * 分页查询优惠卷
     * @param couponDto
     * @return
     */
    @GetMapping("page")
    public List<CouponDto> page(CouponDto couponDto){
        CouponBo converttobo = CouponDtoConvert.INSERT.CONVERTTOBO(couponDto);
        List<CouponBo> list=couponService.page(converttobo);
        List<CouponDto> couponDtos = CouponDtoConvert.INSERT.CONVERTTODTOlIST(list);
        return couponDtos;


    }



}
