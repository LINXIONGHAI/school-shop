package com.itlin.coupon.service.impl;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.emun.CouponLockState;
import com.itlin.common.emun.CouponStatus;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.coupon.mq.CouponMQLockReq;
import com.itlin.common.util.JsonData;
import com.itlin.coupon.config.RabbitMqConfig;
import com.itlin.coupon.entity.CouponRecord;
import com.itlin.coupon.entity.CouponTask;
import com.itlin.coupon.dao.CouponTaskDao;
import com.itlin.coupon.service.CouponRecordService;
import com.itlin.coupon.service.CouponTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * (CouponTask)表服务实现类
 *
 * @author makejava
 * @since 2024-04-30 14:42:51
 */
@Service("couponTaskService")
@Slf4j
public class CouponTaskServiceImpl implements CouponTaskService {
    @Resource
    private CouponTaskDao couponTaskDao;

    @Resource
    private CouponRecordService couponRecordService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RabbitMqConfig rabbitMqConfig;

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
    @Transactional
    public JsonData lockCoupon(String couponId,String orderId) {
        LoginUser loginUser = LoginThreadLocal.get();
        String userId = String.valueOf(loginUser.getId());
        CouponRecord couponRecord=couponRecordService.getByIdAndUserId(couponId,userId,
                CouponStatus.NEW.name(),new Date());
        if (couponRecord == null) {
            return JsonData.buildError("优惠卷不存在");
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

        //发送消息到mq延时队列
        CouponMQLockReq mqLockReq = new CouponMQLockReq();
        String messageId = UUID.randomUUID().toString();
        mqLockReq.setMessageId(messageId);
        mqLockReq.setTaskId(couponTask.getId().toString());
        mqLockReq.setOutTranceId(orderId);
        mqLockReq.setUserId(userId);
        rabbitTemplate.convertAndSend(rabbitMqConfig.getEventExchange(),
                                rabbitMqConfig.getCouponReleaseDelayRoutingKey(),mqLockReq);
        log.info("发送优惠卷延迟消息成功");


        return JsonData.buildSuccess(couponRecord.getPrice());


    }
}
