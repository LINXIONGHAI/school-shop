package com.itlin.coupon.controller;

import com.itlin.common.emun.CouponCategoryEnum;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.util.JsonData;
import com.itlin.coupon.bo.CouponRecordBo;
import com.itlin.coupon.convert.CouponDtoConvert;
import com.itlin.coupon.convert.CouponRecoredDtoConvert;
import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import com.itlin.coupon.dto.CouponRecordDto;
import com.itlin.coupon.entity.Coupon;
import com.itlin.coupon.entity.CouponRecord;
import com.itlin.coupon.service.CouponRecordService;
import com.itlin.coupon.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/couponRecord/v1")
@Slf4j
public class CouponRecordController {

    @Resource
    private CouponRecordService couponRecordService;


    /**
     * 领取优惠卷
     * @param
     * @return
     */
    @GetMapping("save")
    public JsonData save(String couponId,int type){
        couponRecordService.save(couponId, CouponCategoryEnum.getByCode(type));

        return JsonData.buildSuccess();

    }

    /**
     * 优惠卷记录分页
     * @param
     * @return
     */
    @GetMapping("page")
    public JsonData page(CouponRecordDto couponRecordDto){
        CouponRecordBo converttobo = CouponRecoredDtoConvert.INSERT.CONVERTTOBO(couponRecordDto);
        List<CouponRecordBo> list=couponRecordService.page(converttobo);
        List<CouponRecordDto> couponRecordDtos = CouponRecoredDtoConvert.INSERT.CONVERTTODTOlIST(list);
        return JsonData.buildSuccess(couponRecordDtos);

    }

    /**
     * 优惠卷记录详情
     */
    @GetMapping("getById/{counp_record_id}")
    public JsonData getById(@PathVariable Long counp_record_id){
        CouponRecordBo couponRecordBo = couponRecordService.getById(counp_record_id);
        CouponRecordDto couponRecordDto = CouponRecoredDtoConvert.INSERT.CONVERTTODTOlIST(couponRecordBo);
        return JsonData.buildSuccess(couponRecordDto);

    }


    @ApiOperation("RPC")
    @GetMapping("/new_user_coupon")
    public JsonData loginCoupon(@RequestParam("userId") String userId){
       return couponRecordService.loginCoupon(userId);

    }





}
