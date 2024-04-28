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
import com.itlin.school.auth.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
            String key = redisUtil.buildKey("user-auth", "token", token);
            redisUtil.setNx(key, token, 1L, TimeUnit.DAYS);
            return token;
        }
        throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR);
    }

    @Override
    public JsonData expire(HttpServletRequest request) {
        String token = request.getHeader("itlin.token");
        if(token==null){
            throw  new BizException(BizCodeEnum.ACCOUNT_EXPIRE);
        }
        String key = redisUtil.buildKey("user-auth", "token", token);
        String redisToken = redisUtil.get(key);
        if(redisToken==null){
            String reftoken = CommonUtil.geneJsonWebToken(LoginThreadLocal.get());
            return JsonData.buildSuccess(reftoken);
//            throw new BizException(BizCodeEnum.ACCOUNT_EXPIRE);
        }
        redisUtil.expire(key,1L,TimeUnit.DAYS);
        return JsonData.buildSuccess();
    }


    /**
     * 获取邮箱key
     */
    public String getEmailKey(HttpServletRequest request, String email) {
        String ipAddr = CommonUtil.getIpAddr(request);
        return redisUtil.buildKey("user-auth", "email", ipAddr, email);
    }
}
