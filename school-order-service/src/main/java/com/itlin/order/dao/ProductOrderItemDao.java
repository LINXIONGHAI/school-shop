package com.itlin.order.dao;

import com.itlin.order.entity.ProductOrderItem;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ProductOrderItem)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-29 19:38:43
 */
public interface ProductOrderItemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductOrderItem queryById(Long id);


    /**
     * 统计总行数
     *
     * @param productOrderItem 查询条件
     * @return 总行数
     */
    long count(ProductOrderItem productOrderItem);

    /**
     * 新增数据
     *
     * @param productOrderItem 实例对象
     * @return 影响行数
     */
    int insert(ProductOrderItem productOrderItem);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductOrderItem> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProductOrderItem> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductOrderItem> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ProductOrderItem> entities);

    /**
     * 修改数据
     *
     * @param productOrderItem 实例对象
     * @return 影响行数
     */
    int update(ProductOrderItem productOrderItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

