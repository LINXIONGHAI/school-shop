package com.itlin.school.auth.controller;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.JsonData;
import com.itlin.redis.util.RedisUtil;
import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.convert.UserDtoConvert;
import com.itlin.school.auth.dto.UserDto;
import com.itlin.school.auth.entity.UserDo;
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


    @GetMapping("get/{user_id}")
    @ApiOperation("根据用户id获取用户信息")
    public JsonData get(@PathVariable Long user_id) {
        log.info("UserController:get:user_id={}", user_id);
        try {
            UserDo userDo = userService.queryById(user_id);
            return JsonData.buildSuccess(userDo);
        } catch (Exception e) {
            log.error("UserController:get:erro={}", e.getMessage());
            log.error(BizCodeEnum.SERVICE.getMessage());
            throw new BizException(BizCodeEnum.SERVICE);
        }
    }

    @PostMapping("register")
    @ApiOperation("用户注册")
    public JsonData register(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {
        log.info("UserController:register:UserDto={}", userDto);
        try {
            UserBo userBo = UserDtoConvert.INSERT.UserBoConvert(userDto);
            userService.register(request, userBo);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            BizException bizException= (BizException) e;
            log.error("UserController:register:erro={}", e.getMessage());
            log.error(BizCodeEnum.SERVICE.getMessage());
            throw  bizException;
        }
    }


}
