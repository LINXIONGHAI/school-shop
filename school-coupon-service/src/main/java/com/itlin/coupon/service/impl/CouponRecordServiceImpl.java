package com.itlin.coupon.service.impl;

import com.itlin.coupon.entity.CouponRecord;
import com.itlin.coupon.dao.CouponRecordDao;
import com.itlin.coupon.service.CouponRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (CouponRecord)表服务实现类
 *
 * @author makejava
 * @since 2024-04-28 20:25:03
 */
@Service("couponRecordService")
public class CouponRecordServiceImpl implements CouponRecordService {
    @Resource
    private CouponRecordDao couponRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CouponRecord queryById(Object id) {
        return this.couponRecordDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param couponRecord 实例对象
     * @return 实例对象
     */
    @Override
    public CouponRecord insert(CouponRecord couponRecord) {
        this.couponRecordDao.insert(couponRecord);
        return couponRecord;
    }

    /**
     * 修改数据
     *
     * @param couponRecord 实例对象
     * @return 实例对象
     */
    @Override
    public CouponRecord update(CouponRecord couponRecord) {
        this.couponRecordDao.update(couponRecord);
        return this.queryById(couponRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.couponRecordDao.deleteById(id) > 0;
    }
}
