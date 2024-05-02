package com.itlin.coupon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itlin.common.emun.*;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.coupon.feign.ProduceOrederFeignRpc;
import com.itlin.coupon.mq.CouponMQLockReq;
import com.itlin.common.util.JsonData;
import com.itlin.coupon.bo.CouponRecordBo;
import com.itlin.coupon.convert.CouponRecoredBoConvert;
import com.itlin.coupon.dao.CouponTaskDao;
import com.itlin.coupon.entity.Coupon;
import com.itlin.coupon.entity.CouponRecord;
import com.itlin.coupon.dao.CouponRecordDao;
import com.itlin.coupon.entity.CouponTask;
import com.itlin.coupon.service.CouponRecordService;
import com.itlin.coupon.service.CouponService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @Resource
    private CouponTaskDao couponTaskDao;

    @Resource
    private ProduceOrederFeignRpc produceOrederFeignRpc;

    @Resource
    private HttpServletRequest httpServletRequest;

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
    public void save(String couponId, CouponCategoryEnum couponCategoryEnum) {
        RLock lock = redissonClient.getLock("lock:coupon:" + couponId);
//阻塞式等待，一个线程获取锁后，其他线程只能等待，和原生的方式循环调用不一样
        lock.lock();

        try {
            Coupon coupon1 = new Coupon();
            coupon1.setId(Long.parseLong(couponId));
            coupon1.setPublish(CouponPublishEnum.PUBLISH.name());
            coupon1.setCategory(couponCategoryEnum.name());
            Coupon coupon = couponService.query(coupon1);
            if (coupon == null) {
                throw new BizException(BizCodeEnum.COUPON_NO_PUBLISH);
            }
            if (coupon.getPublishCount() <= 0) {
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
            if (count >= coupon.getUserLimit()) {
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

    @Override
    public List<CouponRecordBo> page(CouponRecordBo converttobo) {
        CouponRecord couponRecord = CouponRecoredBoConvert.INSERT.CONVERTTODo(converttobo);
        LoginUser loginUser = LoginThreadLocal.get();
        Long userId = Long.parseLong(String.valueOf(loginUser.getId()));
        PageHelper.startPage(converttobo.getPage(), converttobo.getPageSize());
        List<CouponRecord> list = couponRecordDao.page(userId);
        PageInfo<CouponRecord> pageInfo = new PageInfo<>(list);
        List<CouponRecord> list1 = pageInfo.getList();
        List<CouponRecordBo> couponRecordBos = CouponRecoredBoConvert.INSERT.CONVERTTODTOlIST(list1);
        return couponRecordBos;


    }

    @Override
    public CouponRecordBo getById(Long counpRecordId) {
        LoginUser loginUser = LoginThreadLocal.get();
        CouponRecord couponRecord = couponRecordDao.queryByIdAndUid(counpRecordId, Long.parseLong(String.valueOf(loginUser.getId())));
        CouponRecordBo couponRecordBo = CouponRecoredBoConvert.INSERT.CONVERTTOBo(couponRecord);
        return couponRecordBo;

    }

    /**
     * 新用户发放优惠卷
     *
     * @param
     * @return
     */
    @Override
    public JsonData loginCoupon(String userId) {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(userId);
        LoginThreadLocal.set(loginUser);
        Coupon coupon = new Coupon();
        coupon.setCategory(CouponCategoryEnum.NEW_USER.name());
        List<Coupon> list = couponService.queryList(coupon);
        if (list == null) {
            throw new BizException(BizCodeEnum.COUPON_NEW_ERRO);
        }
        list.stream().forEach(item -> {
            Long couponId = item.getId();
            this.save(String.valueOf(couponId), CouponCategoryEnum.NEW_USER);
        });


        return JsonData.buildSuccess();
    }

    @Override
    public CouponRecord getByIdAndUserId(String couponId, String userId, String states, Date date) {

        return couponRecordDao.getByIdAndUserId(couponId, userId, states, date);


    }

    @Override
    @Transactional
    public Boolean unlock(CouponMQLockReq couponMQLockReq) {
//        String userId = couponMQLockReq.getUserId();
        String outTranceId = couponMQLockReq.getOutTranceId();
//        String taskId = couponMQLockReq.getTaskId();
        //查询订单是否支付
        JsonData byOutTrane = produceOrederFeignRpc.getByOutTrane(outTranceId);
        if (byOutTrane.getCode() == 1) {
            String state = byOutTrane.getData().toString();
            if (state.equals(OrderStatues.NEW.name())) {
                return false;
            } else if (state.equals(OrderStatues.PAY.name())){
                return idTack(couponMQLockReq);
            }else {
                return update2(couponMQLockReq);
            }
        } else {
            return update2(couponMQLockReq);
        }
//        return update(couponMQLockReq);


    }

    /**
     * 标记优惠卷已使用
     */
    public Boolean idTack(CouponMQLockReq couponMQLockReq){
        String outTranceId = couponMQLockReq.getOutTranceId();
        String userId = couponMQLockReq.getUserId();
        String taskId = couponMQLockReq.getTaskId();
        CouponTask couponTask = couponTaskDao.queryByOutTranceId(outTranceId);
        if(couponTask==null){
            return true;
        }
        if (couponTask.getLockState().equals(CouponLockState.LOCK.name())) {
            int update = couponRecordDao.updateLockStates(couponTask.getCouponRecordId(), userId, CouponStatus.USED.name());
            int delete = couponTaskDao.updateState(outTranceId,Long.parseLong(taskId),CouponLockState.FINISH.name());
            if (update == delete) {
                return true;
            }
        }
        return true;

    }

    /**
     * 释放
     * @param couponMQLockReq
     * @return
     */
    public Boolean update2(CouponMQLockReq couponMQLockReq) {
        String outTranceId = couponMQLockReq.getOutTranceId();
        String userId = couponMQLockReq.getUserId();
        String taskId = couponMQLockReq.getTaskId();
        CouponTask couponTask = couponTaskDao.queryByOutTranceId(outTranceId);
        if(couponTask==null&&couponTask.getLockState()!=CouponLockState.FINISH.name()){
            return true;
        }
        if(couponTask==null&&couponTask.getLockState()!=CouponLockState.CANCEL.name()){
            return true;
        }
        if (couponTask.getLockState().equals(CouponLockState.LOCK.name())) {
            int update = couponRecordDao.updateLockStates(couponTask.getCouponRecordId(), userId, CouponStatus.NEW.name());
            int delete = couponTaskDao.deleteById(Long.parseLong(taskId));
            if (update == delete) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }
}
