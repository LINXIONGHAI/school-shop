package com.itlin.coupon.service;

import com.itlin.common.util.JsonData;
import com.itlin.coupon.entity.CouponTask;

/**
 * (CouponTask)表服务接口
 *
 * @author makejava
 * @since 2024-04-30 14:42:51
 */
public interface CouponTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CouponTask queryById(Long id);


    /**
     * 新增数据
     *
     * @param couponTask 实例对象
     * @return 实例对象
     */
    CouponTask insert(CouponTask couponTask);

    /**
     * 修改数据
     *
     * @param couponTask 实例对象
     * @return 实例对象
     */
    CouponTask update(CouponTask couponTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    JsonData lockCoupon(String couponId,String orderId);
}
