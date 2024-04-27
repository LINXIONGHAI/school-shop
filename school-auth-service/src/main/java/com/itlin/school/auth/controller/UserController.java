package com.itlin.school.auth.controller;

import com.itlin.school.auth.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {


    @Resource
    private UserService userService;

    @RequestMapping("get")
    public Object get(){
        return userService.queryById(null);
    }
}
