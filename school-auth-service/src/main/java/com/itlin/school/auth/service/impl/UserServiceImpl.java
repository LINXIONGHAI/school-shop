package com.itlin.school.auth.service.impl;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.CommonUtil;
import com.itlin.common.util.JsonData;
import com.itlin.redis.util.RedisUtil;
import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.convert.LoginUserConvert;
import com.itlin.school.auth.convert.UserBoConvert;
import com.itlin.school.auth.entity.UserDo;
import com.itlin.school.auth.dao.UserDao;
import com.itlin.school.auth.feign.CouponFeignRpc;
import com.itlin.school.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private CouponFeignRpc couponFeignRpc;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserBo queryById(Object id) {
        UserDo userDo = this.userDao.queryById(id);
        UserBo userBo = UserBoConvert.INSERT.UserBoConvert(userDo);
        return userBo;
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
        UserBo userBo = this.queryById(userDo.getId());
        UserDo userDo1 = UserBoConvert.INSERT.UserDoConvert(userBo);
        return userDo1;
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
            //发送优惠卷
            Object id = userDo.getId();
            JsonData jsonData = couponFeignRpc.loginCoupon(String.valueOf(id));
            return;
        }
        throw new BizException(BizCodeEnum.CODE_ERROR);

    }

    /**
     * 根据用户信息查询
     */

    public UserDo query(UserDo userDo) {
        return userDao.query(userDo);
    }


    /**
     * 用户登录
     *
     * @param userBo
     * @return
     */
    @Override
    public String login(UserBo userBo) {
        UserDo userDo = UserBoConvert.INSERT.UserDoConvert(userBo);
        userDo.setPwd(null);
        UserDo queryDo = this.query(userDo);
        if (queryDo == null) {
            throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR);
        }
        String pwd = queryDo.getPwd();
        String secret = queryDo.getSecret();
        String md5Crypt = Md5Crypt.md5Crypt(userBo.getPwd().getBytes(), secret);
        if (md5Crypt.equals(pwd)) {
            //todo 颁发token
            LoginUser loginUser = LoginUserConvert.INSERT.UserLoginConvert(queryDo);
            String token = CommonUtil.geneJsonWebToken(loginUser);
            //redis存token
            String key = redisUtil.buildKey("user-auth", "token", queryDo.getId().toString());
            redisUtil.setNx(key, token, 10L, TimeUnit.DAYS);
            return token;
        }
        throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR);
    }

    @Override
    public JsonData expire(HttpServletRequest request) {
        String token = request.getHeader("itlin.token");
        if (token == null) {
            throw new BizException(BizCodeEnum.ACCOUNT_EXPIRE);
        }
        LoginUser loginUser = LoginThreadLocal.get();
        String key = redisUtil.buildKey("user-auth", "token", String.valueOf(loginUser.getId()));
        String redisToken = redisUtil.get(key);
        Map<String, Object> map = new HashMap<>();
        if (redisToken == null) {
            LoginUser user = LoginThreadLocal.get();
            String reftoken = CommonUtil.geneJsonWebToken(user);
            String refKey = redisUtil.buildKey("user-auth", "token", String.valueOf(user.getId()));
            redisUtil.setNx(refKey, reftoken, 10L, TimeUnit.DAYS);
            map.put("expearTime", System.currentTimeMillis() + 1000 * 60 * 60 * 5);
            map.put("redisTime", System.currentTimeMillis() + 1000 * 60 * 60 * 10);
            map.put("authtoken", reftoken);
            return JsonData.buildSuccess(map);
        }
        redisUtil.del(key);
        LoginUser loginUser1 = LoginThreadLocal.get();
        String reftoken = CommonUtil.geneJsonWebToken(loginUser1);
        String refKey = redisUtil.buildKey("user-auth", "token", String.valueOf(loginUser1.getId()));
        redisUtil.setNx(refKey, reftoken, 10L, TimeUnit.DAYS);
        map.put("expearTime", System.currentTimeMillis() + 1000 * 60 * 60 * 5);
        map.put("redisTime", System.currentTimeMillis() + 1000 * 60 * 60 * 10);
        map.put("authtoken", reftoken);
        return JsonData.buildSuccess(map);
    }


    /**
     * 获取邮箱key
     */
    public String getEmailKey(HttpServletRequest request, String email) {
        String ipAddr = CommonUtil.getIpAddr(request);
        return redisUtil.buildKey("user-auth", "email", ipAddr, email);
    }
}
