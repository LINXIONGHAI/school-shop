package com.itlin.coupon.convert;

import com.itlin.coupon.bo.CouponRecordBo;
import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import com.itlin.coupon.dto.CouponRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponRecoredDtoConvert {

    CouponRecoredDtoConvert INSERT= Mappers.getMapper(CouponRecoredDtoConvert.class);
    CouponRecordBo CONVERTTOBO(CouponRecordDto couponRecordDto);
    List<CouponRecordDto>  CONVERTTODTOlIST(List<CouponRecordBo> list);
    CouponRecordDto  CONVERTTODTOlIST(CouponRecordBo couponRecordBo);


}
