package com.itlin.coupon.convert;

import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponDtoConvert {

    CouponDtoConvert INSERT= Mappers.getMapper(CouponDtoConvert.class);
    CouponBo  CONVERTTOBO(CouponDto couponDto);
    List<CouponDto>  CONVERTTODTOlIST(List<CouponBo> list);


}
