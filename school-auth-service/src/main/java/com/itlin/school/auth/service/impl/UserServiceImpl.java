package com.itlin.school.auth.service.impl;

import com.itlin.redis.util.RedisUtil;
import com.itlin.school.auth.entity.UserDo;
import com.itlin.school.auth.dao.UserDao;
import com.itlin.school.auth.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-27 21:56:29
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserDo queryById(Object id) {
        return this.userDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param userDo 实例对象
     * @return 实例对象
     */
    @Override
    public UserDo insert(UserDo userDo) {
        this.userDao.insert(userDo);
        return userDo;
    }

    /**
     * 修改数据
     *
     * @param userDo 实例对象
     * @return 实例对象
     */
    @Override
    public UserDo update(UserDo userDo) {
        this.userDao.update(userDo);
        return this.queryById(userDo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.userDao.deleteById(id) > 0;
    }
}
