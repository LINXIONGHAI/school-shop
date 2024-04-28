package com.itlin.coupon.convert;

import com.itlin.coupon.bo.CouponRecordBo;
import com.itlin.coupon.dto.CouponRecordDto;
import com.itlin.coupon.entity.CouponRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponRecoredBoConvert {

    CouponRecoredBoConvert INSERT= Mappers.getMapper(CouponRecoredBoConvert.class);
    CouponRecord CONVERTTODo(CouponRecordBo couponRecordBo);
    List<CouponRecordBo>  CONVERTTODTOlIST(List<CouponRecord> list);
    CouponRecordBo CONVERTTOBo(CouponRecord couponRecord);

}
