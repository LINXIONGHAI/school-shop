package com.itlin.school.auth.controller;

import com.itlin.school.auth.service.AddressService;
import com.itlin.school.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/address/v1")
@Slf4j
public class AddressController {


    @Resource
    private AddressService addressService;

    @RequestMapping("get/{address_id}")
    public Object get(@PathVariable Long address_id){
        log.info("AddressController:get:addressId={}",address_id);
        return addressService.queryById(address_id);
    }
}
