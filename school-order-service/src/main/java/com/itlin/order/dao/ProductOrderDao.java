package com.itlin.order.dao;

import com.itlin.order.entity.ProductOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ProductOrder)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-29 19:37:35
 */
public interface ProductOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductOrder queryById(Long id);


    /**
     * 统计总行数
     *
     * @param productOrder 查询条件
     * @return 总行数
     */
    long count(ProductOrder productOrder);

    /**
     * 新增数据
     *
     * @param productOrder 实例对象
     * @return 影响行数
     */
    int insert(ProductOrder productOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProductOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ProductOrder> entities);

    /**
     * 修改数据
     *
     * @param productOrder 实例对象
     * @return 影响行数
     */
    int update(ProductOrder productOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

