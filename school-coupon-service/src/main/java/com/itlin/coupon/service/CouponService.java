package com.itlin.coupon.service;

import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import com.itlin.coupon.entity.Coupon;

import java.util.List;

/**
 * (Coupon)表服务接口
 *
 * @author makejava
 * @since 2024-04-28 20:25:04
 */
public interface CouponService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Coupon queryById(Long id);


    /**
     * 新增数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    Coupon insert(Coupon coupon);

    /**
     * 修改数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    Coupon update(Coupon coupon);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<CouponBo> page(CouponBo converttoboo);
}
