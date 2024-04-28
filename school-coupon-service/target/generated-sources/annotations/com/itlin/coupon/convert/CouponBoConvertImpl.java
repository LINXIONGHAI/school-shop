package com.itlin.coupon.convert;

import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.entity.Coupon;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T22:33:17+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class CouponBoConvertImpl implements CouponBoConvert {

    @Override
    public Coupon CONVERTTODO(CouponBo couponBo) {
        if ( couponBo == null ) {
            return null;
        }

        Coupon coupon = new Coupon();

        coupon.setId( couponBo.getId() );
        coupon.setCategory( couponBo.getCategory() );
        coupon.setPublish( couponBo.getPublish() );
        coupon.setCouponImg( couponBo.getCouponImg() );
        coupon.setCouponTitle( couponBo.getCouponTitle() );
        coupon.setPrice( couponBo.getPrice() );
        coupon.setUserLimit( couponBo.getUserLimit() );
        coupon.setStartTime( couponBo.getStartTime() );
        coupon.setEndTime( couponBo.getEndTime() );
        coupon.setPublishCount( couponBo.getPublishCount() );
        coupon.setStock( couponBo.getStock() );
        coupon.setCreateTime( couponBo.getCreateTime() );
        coupon.setConditionPrice( couponBo.getConditionPrice() );

        return coupon;
    }

    @Override
    public List<CouponBo> CONVERTTOBolIST(List<Coupon> list) {
        if ( list == null ) {
            return null;
        }

        List<CouponBo> list1 = new ArrayList<CouponBo>( list.size() );
        for ( Coupon coupon : list ) {
            list1.add( couponToCouponBo( coupon ) );
        }

        return list1;
    }

    protected CouponBo couponToCouponBo(Coupon coupon) {
        if ( coupon == null ) {
            return null;
        }

        CouponBo couponBo = new CouponBo();

        couponBo.setId( coupon.getId() );
        couponBo.setCategory( coupon.getCategory() );
        couponBo.setPublish( coupon.getPublish() );
        couponBo.setCouponImg( coupon.getCouponImg() );
        couponBo.setCouponTitle( coupon.getCouponTitle() );
        couponBo.setPrice( coupon.getPrice() );
        couponBo.setUserLimit( coupon.getUserLimit() );
        couponBo.setStartTime( coupon.getStartTime() );
        couponBo.setEndTime( coupon.getEndTime() );
        couponBo.setPublishCount( coupon.getPublishCount() );
        couponBo.setStock( coupon.getStock() );
        couponBo.setCreateTime( coupon.getCreateTime() );
        couponBo.setConditionPrice( coupon.getConditionPrice() );

        return couponBo;
    }
}
