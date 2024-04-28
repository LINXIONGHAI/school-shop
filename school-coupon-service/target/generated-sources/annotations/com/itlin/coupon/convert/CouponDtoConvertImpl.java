package com.itlin.coupon.convert;

import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-29T01:31:37+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class CouponDtoConvertImpl implements CouponDtoConvert {

    @Override
    public CouponBo CONVERTTOBO(CouponDto couponDto) {
        if ( couponDto == null ) {
            return null;
        }

        CouponBo couponBo = new CouponBo();

        couponBo.setPage( couponDto.getPage() );
        couponBo.setPageSize( couponDto.getPageSize() );
        couponBo.setId( couponDto.getId() );
        couponBo.setCategory( couponDto.getCategory() );
        couponBo.setPublish( couponDto.getPublish() );
        couponBo.setCouponImg( couponDto.getCouponImg() );
        couponBo.setCouponTitle( couponDto.getCouponTitle() );
        couponBo.setPrice( couponDto.getPrice() );
        couponBo.setUserLimit( couponDto.getUserLimit() );
        couponBo.setStartTime( couponDto.getStartTime() );
        couponBo.setEndTime( couponDto.getEndTime() );
        couponBo.setPublishCount( couponDto.getPublishCount() );
        couponBo.setStock( couponDto.getStock() );
        couponBo.setCreateTime( couponDto.getCreateTime() );
        couponBo.setConditionPrice( couponDto.getConditionPrice() );

        return couponBo;
    }

    @Override
    public List<CouponDto> CONVERTTODTOlIST(List<CouponBo> list) {
        if ( list == null ) {
            return null;
        }

        List<CouponDto> list1 = new ArrayList<CouponDto>( list.size() );
        for ( CouponBo couponBo : list ) {
            list1.add( couponBoToCouponDto( couponBo ) );
        }

        return list1;
    }

    protected CouponDto couponBoToCouponDto(CouponBo couponBo) {
        if ( couponBo == null ) {
            return null;
        }

        CouponDto couponDto = new CouponDto();

        couponDto.setPage( couponBo.getPage() );
        couponDto.setPageSize( couponBo.getPageSize() );
        couponDto.setId( couponBo.getId() );
        couponDto.setCategory( couponBo.getCategory() );
        couponDto.setPublish( couponBo.getPublish() );
        couponDto.setCouponImg( couponBo.getCouponImg() );
        couponDto.setCouponTitle( couponBo.getCouponTitle() );
        couponDto.setPrice( couponBo.getPrice() );
        couponDto.setUserLimit( couponBo.getUserLimit() );
        couponDto.setStartTime( couponBo.getStartTime() );
        couponDto.setEndTime( couponBo.getEndTime() );
        couponDto.setPublishCount( couponBo.getPublishCount() );
        couponDto.setStock( couponBo.getStock() );
        couponDto.setCreateTime( couponBo.getCreateTime() );
        couponDto.setConditionPrice( couponBo.getConditionPrice() );

        return couponDto;
    }
}
