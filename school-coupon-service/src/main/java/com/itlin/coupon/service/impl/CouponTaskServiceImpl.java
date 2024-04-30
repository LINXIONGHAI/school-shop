package com.itlin.coupon.service.impl;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.emun.CouponLockState;
import com.itlin.common.emun.CouponStatus;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.JsonData;
import com.itlin.coupon.entity.CouponRecord;
import com.itlin.coupon.entity.CouponTask;
import com.itlin.coupon.dao.CouponTaskDao;
import com.itlin.coupon.service.CouponRecordService;
import com.itlin.coupon.service.CouponService;
import com.itlin.coupon.service.CouponTaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (CouponTask)表服务实现类
 *
 * @author makejava
 * @since 2024-04-30 14:42:51
 */
@Service("couponTaskService")
public class CouponTaskServiceImpl implements CouponTaskService {
    @Resource
    private CouponTaskDao couponTaskDao;

    @Resource
    private CouponRecordService couponRecordService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CouponTask queryById(Long id) {
        return this.couponTaskDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param couponTask 实例对象
     * @return 实例对象
     */
    @Override
    public CouponTask insert(CouponTask couponTask) {
        this.couponTaskDao.insert(couponTask);
        return couponTask;
    }

    /**
     * 修改数据
     *
     * @param couponTask 实例对象
     * @return 实例对象
     */
    @Override
    public CouponTask update(CouponTask couponTask) {
        this.couponTaskDao.update(couponTask);
        return this.queryById(couponTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.couponTaskDao.deleteById(id) > 0;
    }


    @Override
    public JsonData lockCoupon(String couponId,String orderId) {
        LoginUser loginUser = LoginThreadLocal.get();
        String userId = String.valueOf(loginUser.getId());
        CouponRecord couponRecord=couponRecordService.getByIdAndUserId(couponId,userId,
                CouponStatus.NEW.name(),new Date());
        if (couponRecord == null) {
            throw new BizException(BizCodeEnum.COUPONRECOUD_NO_NULL);
        }
        CouponTask couponTask = new CouponTask();

        couponTask.setCouponRecordId(Long.parseLong(couponRecord.getId().toString()));
        couponTask.setCreateTime(new Date());
        couponTask.setOutTradeNo(orderId);
        couponTask.setLockState(CouponLockState.LOCK.name());
        //插入使用优惠卷task
        couponTaskDao.insert(couponTask);
//        修改优惠卷记录
        couponRecord.setUseState(CouponStatus.USED.name());
        couponRecordService.update(couponRecord);

        return JsonData.buildSuccess();


    }
}
