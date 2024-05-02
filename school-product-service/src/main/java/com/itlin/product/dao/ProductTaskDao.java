package com.itlin.product.dao;

import com.itlin.product.entity.ProductTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (ProductTask)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-30 19:40:59
 */
public interface ProductTaskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductTask queryById(Long id);


    /**
     * 统计总行数
     *
     * @param productTask 查询条件
     * @return 总行数
     */
    long count(ProductTask productTask);

    /**
     * 新增数据
     *
     * @param productTask 实例对象
     * @return 影响行数
     */
    int insert(ProductTask productTask);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductTask> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProductTask> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductTask> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ProductTask> entities);

    /**
     * 修改数据
     *
     * @param productTask 实例对象
     * @return 影响行数
     */
    int update(ProductTask productTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

     void lock(ProductTask productTask);

}

