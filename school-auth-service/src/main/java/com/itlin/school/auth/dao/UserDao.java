package com.itlin.school.auth.dao;

import com.itlin.school.auth.entity.UserDo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-27 21:56:29
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserDo queryById(Object id);


    /**
     * 统计总行数
     *
     * @param userDo 查询条件
     * @return 总行数
     */
    long count(UserDo userDo);

    /**
     * 新增数据
     *
     * @param userDo 实例对象
     * @return 影响行数
     */
    int insert(UserDo userDo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserDo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserDo> entities);

    /**
     * 修改数据
     *
     * @param userDo 实例对象
     * @return 影响行数
     */
    int update(UserDo userDo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

    UserDo query(UserDo userDo);
}

