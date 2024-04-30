package com.itlin.coupon.dao;

import com.itlin.coupon.entity.CouponTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (CouponTask)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-30 14:42:50
 */
public interface CouponTaskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CouponTask queryById(Long id);


    /**
     * 统计总行数
     *
     * @param couponTask 查询条件
     * @return 总行数
     */
    long count(CouponTask couponTask);

    /**
     * 新增数据
     *
     * @param couponTask 实例对象
     * @return 影响行数
     */
    int insert(CouponTask couponTask);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CouponTask> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CouponTask> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CouponTask> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CouponTask> entities);

    /**
     * 修改数据
     *
     * @param couponTask 实例对象
     * @return 影响行数
     */
    int update(CouponTask couponTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

