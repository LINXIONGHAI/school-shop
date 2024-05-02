package com.itlin.coupon.service;

import com.itlin.common.emun.CouponCategoryEnum;
import com.itlin.coupon.mq.CouponMQLockReq;
import com.itlin.common.util.JsonData;
import com.itlin.coupon.bo.CouponRecordBo;
import com.itlin.coupon.entity.CouponRecord;

import java.util.Date;
import java.util.List;

/**
 * (CouponRecord)表服务接口
 *
 * @author makejava
 * @since 2024-04-28 20:25:03
 */
public interface CouponRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CouponRecord queryById(Object id);


    /**
     * 新增数据
     *
     * @param couponRecord 实例对象
     * @return 实例对象
     */
    CouponRecord insert(CouponRecord couponRecord);

    /**
     * 修改数据
     *
     * @param couponRecord 实例对象
     * @return 实例对象
     */
    CouponRecord update(CouponRecord couponRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

    void save(String couponId, CouponCategoryEnum couponCategoryEnum);

    List<CouponRecordBo> page(CouponRecordBo converttobo);

    CouponRecordBo getById(Long counpRecordId);

    JsonData loginCoupon(String userId);

    CouponRecord getByIdAndUserId(String couponId, String userId, String name, Date date);

    /**
     * 释放释放优惠卷
     * @param couponMQLockReq
     * @return
     */
    Boolean unlock(CouponMQLockReq couponMQLockReq);

}
