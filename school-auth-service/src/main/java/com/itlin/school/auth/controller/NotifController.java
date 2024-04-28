package com.itlin.school.auth.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.CommonUtil;
import com.itlin.common.util.JsonData;
import com.itlin.redis.util.RedisUtil;
import com.itlin.school.auth.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/notif/v1")
@Slf4j
@Api(tags = "通知模块")
public class NotifController {


    @Resource
    private DefaultKaptcha captchaProducer;

    @Resource
    private RedisUtil redisUtil;


    @Resource
    private MailService mailService;


    @GetMapping("getCapter")
    @ApiOperation("获取验证码")
    public void getCapter(HttpServletRequest request, HttpServletResponse response) {

        log.info("UserController:getCapter");
        try {
            String code = captchaProducer.createText();
            log.info("验证码：{}", code);
            String capterKey = getCapterKey(request, code);
            redisUtil.setNx(capterKey, code, 1L, TimeUnit.MINUTES);
            BufferedImage image = captchaProducer.createImage(code);
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            log.error("NotifController:getCapter={}", e.getMessage());
            BizException bizException= (BizException) e;
            throw bizException;
        }
    }

    /**
     * type: 1->邮箱，2->手机验证码验证 , 3->qq注册
     *
     * @return
     */
    @GetMapping("sendEmail")
    public JsonData sendEmail(String email, String code, HttpServletRequest request, HttpServletResponse response) {
        String capterKey = getCapterKey(request, code);
        String redisVal = redisUtil.get(capterKey);
        if (code.equals(redisVal)) {
            redisUtil.del(capterKey);
            String codeEmail = captchaProducer.createText();
            String emailKey = getEmailKey(request, email);

            String val = redisUtil.get(emailKey);
            if (val == null) {
                redisUtil.setNx(emailKey, codeEmail + "-" + System.currentTimeMillis(), 5L, TimeUnit.MINUTES);
                mailService.sendSimpleMail(email, "欢迎注册校园二手交易网", "您的验证码：" + codeEmail + " 五分钟有效哦`");
                return JsonData.buildSuccess();
            }
            long timeMillis = System.currentTimeMillis();
            long redisTime = Long.parseLong(val.split("-")[1]);
            if (timeMillis - redisTime <= 60000) {
                throw new BizException(BizCodeEnum.CODE_LIMITED);
            } else {
                redisUtil.del(emailKey);
                redisUtil.setNx(emailKey, codeEmail + "-" + System.currentTimeMillis(), 5L, TimeUnit.MINUTES);
                mailService.sendSimpleMail(email, "欢迎注册校园二手交易网", "您的验证码：" + codeEmail + " 五分钟有效哦`");
                return JsonData.buildSuccess();
            }

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


    /**
     * 获取验证码key
     */
    public String getCapterKey(HttpServletRequest request, String code) {
        String ipAddr = CommonUtil.getIpAddr(request);
        return redisUtil.buildKey("auth-auth", "capter", ipAddr, code);

    }

}
