package com.itlin.coupon.dao;

import com.itlin.coupon.entity.CouponRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (CouponRecord)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-28 20:25:01
 */
public interface CouponRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CouponRecord queryById(Object id);


    /**
     * 统计总行数
     *
     * @param couponRecord 查询条件
     * @return 总行数
     */
    long count(CouponRecord couponRecord);

    /**
     * 新增数据
     *
     * @param couponRecord 实例对象
     * @return 影响行数
     */
    int insert(CouponRecord couponRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CouponRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CouponRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CouponRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CouponRecord> entities);

    /**
     * 修改数据
     *
     * @param couponRecord 实例对象
     * @return 影响行数
     */
    int update(CouponRecord couponRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

