package com.itlin.school.auth.service.impl;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.CommonUtil;
import com.itlin.redis.util.RedisUtil;
import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.convert.UserBoConvert;
import com.itlin.school.auth.entity.UserDo;
import com.itlin.school.auth.dao.UserDao;
import com.itlin.school.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-27 21:56:29
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private RedisUtil redisUtil;


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


    /**
     * 用户注册
     *
     * @param userBo
     */
    @Override
    public void register(HttpServletRequest request, UserBo userBo) {
        String mail = userBo.getMail();
        String emailCode = userBo.getEmailCode();
        String emailKey = getEmailKey(request, mail);
        String redisVal = redisUtil.get(emailKey);
        if (redisVal == null) {
            throw new BizException(BizCodeEnum.CODE_ERROR);
        }
        if (emailCode.equals(redisVal.split("-")[0])) {
            redisUtil.del(emailKey);
            UserDo userDo = UserBoConvert.INSERT.UserDoConvert(userBo);

            String sale = "$1$" + CommonUtil.getStringNumRandom(8);
            userDo.setSecret(sale);
            String pwdSale = Md5Crypt.md5Crypt(userDo.getPwd().getBytes(), sale);
            userDo.setPwd(pwdSale);
            log.info("pwdSale={}", pwdSale);
            userDao.insert(userDo);
            return;
        }
        throw new BizException(BizCodeEnum.CODE_ERROR);

    }

    /**
     * 获取邮箱key
     */
    public String getEmailKey(HttpServletRequest request, String email) {
        String ipAddr = CommonUtil.getIpAddr(request);
        return redisUtil.buildKey("user-auth", "email", ipAddr, email);
    }
}
