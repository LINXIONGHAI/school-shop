package com.itlin.school.auth.controller;

import com.itlin.school.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/v1")
@Slf4j
@Api(tags = "用户模块")
public class UserController {


    @Resource
    private UserService userService;

    @RequestMapping("get/{user_id}")
    @ApiOperation("根据用户id获取用户信息")
    public Object get(@PathVariable Long user_id){
        log.info("UserController:get:user_id={}",user_id);
        return userService.queryById(user_id);
    }
}
