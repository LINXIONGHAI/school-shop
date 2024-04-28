package com.itlin.coupon.convert;

import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import com.itlin.coupon.entity.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponBoConvert {

    CouponBoConvert INSERT= Mappers.getMapper(CouponBoConvert.class);
    Coupon  CONVERTTODO(CouponBo  couponBo);
    List<CouponBo>  CONVERTTOBolIST(List<Coupon> list);


}
