package com.itlin.school.auth.controller;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.JsonData;
import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.convert.UserDtoConvert;
import com.itlin.school.auth.convert.UserResDtoConvert;
import com.itlin.school.auth.dto.UserReqDto;
import com.itlin.school.auth.dto.UserResDto;
import com.itlin.school.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/v1")
@Slf4j
@Api(tags = "用户模块")
public class UserController {


    @Resource
    private UserService userService;


    @GetMapping("get")
    @ApiOperation("根据用户id获取用户信息")
    public JsonData get() {
        LoginUser loginUser = LoginThreadLocal.get();
        log.info("UserController:get:user_id={}", loginUser);
        try {
            UserBo userBo = userService.queryById(loginUser.getId());
            UserResDto userResDto = UserResDtoConvert.INSERT.UserResDtooConvert(userBo);
            return JsonData.buildSuccess(userResDto);
        } catch (Exception e) {
            e.printStackTrace();
            BizException bizException= (BizException) e;
            throw  bizException;
        }
    }

    @PostMapping("register")
    @ApiOperation("用户注册")
    public JsonData register(@Valid @RequestBody UserReqDto userReqDto, HttpServletRequest request) {
        log.info("UserController:register:UserDto={}", userReqDto);
        try {
            UserBo userBo = UserDtoConvert.INSERT.UserBoConvert(userReqDto);
            userService.register(request, userBo);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            BizException bizException= (BizException) e;
            throw  bizException;
        }
    }

    @PostMapping("login")
    @ApiOperation("用户登录")
    public JsonData login(@Valid @RequestBody UserReqDto userReqDto) {
        log.info("UserController:login:UserDto={}", userReqDto);
        try {
            UserBo userBo = UserDtoConvert.INSERT.UserBoConvert(userReqDto);
            String token=userService.login( userBo);
            return JsonData.buildSuccess(token);
        } catch (Exception e) {
            e.printStackTrace();
            BizException bizException= (BizException) e;
            throw  bizException;
        }
    }

    //    redis时间延续
    @GetMapping("expire")
    @ApiOperation("token重置续期")
    public JsonData expire(HttpServletRequest request) {
       return userService.expire(request);


    }


}
