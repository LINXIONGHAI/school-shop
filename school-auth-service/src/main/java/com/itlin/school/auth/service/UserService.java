package com.itlin.school.auth.service;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.entity.UserDo;

import javax.servlet.http.HttpServletRequest;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2024-04-27 21:56:29
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserDo queryById(Object id);


    /**
     * 新增数据
     *
     * @param userDo 实例对象
     * @return 实例对象
     */
    UserDo insert(UserDo userDo);

    /**
     * 修改数据
     *
     * @param userDo 实例对象
     * @return 实例对象
     */
    UserDo update(UserDo userDo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

    /**
     * 用户注册
     * @param userBo
     */
    void register(HttpServletRequest request, UserBo userBo);

    /**
     * 用户登录
     * @param userBo
     * @return
     */
    String login(UserBo userBo);
}
