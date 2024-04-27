package com.itlin.school.auth.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.CommonUtil;
import com.itlin.common.util.JsonData;
import com.itlin.redis.util.RedisUtil;
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




    @GetMapping("getCapter")
    @ApiOperation("获取验证码")
    public void getCapter(HttpServletRequest request,HttpServletResponse response) {

        log.info("UserController:getCapter");
        try {
            String code = captchaProducer.createText();
            log.info("验证码：{}",code);
            String capterKey = getCapterKey(request);
            redisUtil.setNx(capterKey,code,1L, TimeUnit.MINUTES);
            BufferedImage image = captchaProducer.createImage(code);
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            log.error("NotifController:getCapter={}",e.getMessage());
            log.error(BizCodeEnum.SERVICE.getMessage());
            throw new BizException(BizCodeEnum.SERVICE);
        }
    }

    /**
     * 获取验证码key
     *
     */
    public String getCapterKey(HttpServletRequest request){
        String ipAddr = CommonUtil.getIpAddr(request);
        return redisUtil.buildKey("auth-user","capter",ipAddr);

    }

}
