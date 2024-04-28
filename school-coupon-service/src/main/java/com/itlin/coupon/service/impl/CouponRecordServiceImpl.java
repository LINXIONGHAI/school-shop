package com.itlin.coupon.service.impl;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.emun.CouponPublishEnum;
import com.itlin.common.emun.CouponStatus;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.coupon.entity.Coupon;
import com.itlin.coupon.entity.CouponRecord;
import com.itlin.coupon.dao.CouponRecordDao;
import com.itlin.coupon.service.CouponRecordService;
import com.itlin.coupon.service.CouponService;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.locks.Lock;

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
    @Resource
    private CouponService couponService;

    @Resource
    private RedissonClient redissonClient;

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


    @Transactional
    @Override
    public synchronized void save(String couponId) {
        Lock lock = redissonClient.getLock("lock:coupon:"+couponId);
//阻塞式等待，一个线程获取锁后，其他线程只能等待，和原生的方式循环调用不一样
        lock.lock();

        try {
            Coupon coupon1 = new Coupon();
            coupon1.setId(Long.parseLong(couponId));
            coupon1.setPublish(CouponPublishEnum.PUBLISH.name());
            Coupon coupon = couponService.query(coupon1);
            if(coupon==null ){
                throw new BizException(BizCodeEnum.COUPON_NO_PUBLISH);
            }

            if(coupon.getPublishCount()<=0){
                throw new BizException(BizCodeEnum.COUPON_NO_NULL);
            }
            Date startTime = coupon.getStartTime();
            Date endTime = coupon.getEndTime();
            Date date = new Date();
            if (date.getTime() < startTime.getTime() || date.getTime() > endTime.getTime()) {
                throw new BizException(BizCodeEnum.COUPON_NO_DATE);
            }

            LoginUser loginUser = LoginThreadLocal.get();
            long userId = Long.parseLong(String.valueOf(loginUser.getId()));
            Long id = coupon.getId();

            CouponRecord couponRecord1 = new CouponRecord();
            couponRecord1.setUserId(userId);
            couponRecord1.setCouponId(id);

            //todo
            Integer count = couponRecordDao.query(couponRecord1);
            if(count>=coupon.getUserLimit()){
                throw new BizException(BizCodeEnum.COUNP_RECORD_ERROR);
            }

            CouponRecord couponRecord = new CouponRecord();
            couponRecord.setUserId(userId);
            couponRecord.setPrice(coupon.getPrice());
            couponRecord.setCreateTime(new Date());
            couponRecord.setCouponId(coupon.getId());
            couponRecord.setUseState(CouponStatus.NEW.name());
            couponRecord.setConditionPrice(coupon.getConditionPrice());
            couponRecord.setStartTime(coupon.getStartTime());
            couponRecord.setEndTime(coupon.getEndTime());
            couponRecord.setCouponTitle(coupon.getCouponTitle());
            couponRecordDao.insert(couponRecord);

            //总量和库存减一
            couponService.incroneByCountStack(coupon.getId());
        } finally {
            lock.unlock();

        }


    }
}
