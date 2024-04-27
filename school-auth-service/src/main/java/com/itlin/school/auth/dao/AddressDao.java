package com.itlin.school.auth.dao;

import com.itlin.school.auth.entity.AddressDo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 电商-公司收发货地址表(Address)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-27 21:56:25
 */
public interface AddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AddressDo queryById(Long id);


    /**
     * 统计总行数
     *
     * @param address 查询条件
     * @return 总行数
     */
    long count(AddressDo address);

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 影响行数
     */
    int insert(AddressDo address);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Address> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AddressDo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Address> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AddressDo> entities);

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 影响行数
     */
    int update(AddressDo address);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

