package com.itlin.coupon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itlin.coupon.convert.CouponBoConvert;
import com.itlin.coupon.dto.CouponBo;
import com.itlin.coupon.dto.CouponDto;
import com.itlin.coupon.entity.Coupon;
import com.itlin.coupon.dao.CouponDao;
import com.itlin.coupon.service.CouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Coupon)表服务实现类
 *
 * @author makejava
 * @since 2024-04-28 20:25:04
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {
    @Resource
    private CouponDao couponDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Coupon queryById(Long id) {
        return this.couponDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    @Override
    public Coupon insert(Coupon coupon) {
        this.couponDao.insert(coupon);
        return coupon;
    }

    /**
     * 修改数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    @Override
    public Coupon update(Coupon coupon) {
        this.couponDao.update(coupon);
        return this.queryById(coupon.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.couponDao.deleteById(id) > 0;
    }

    @Override
    public List<CouponBo> page(CouponBo couponBo) {

        Coupon coupon = CouponBoConvert.INSERT.CONVERTTODO(couponBo);
        PageHelper.startPage(couponBo.getPage(),couponBo.getPageSize());
        List<Coupon> list=couponDao.page(coupon);
        PageInfo pageInfo=new PageInfo(list);
        List<Coupon> couponList = pageInfo.getList();
        List<CouponBo> couponBos = CouponBoConvert.INSERT.CONVERTTOBolIST(couponList);

        return couponBos;









    }
}
