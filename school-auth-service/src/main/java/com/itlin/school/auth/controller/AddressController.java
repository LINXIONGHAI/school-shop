package com.itlin.school.auth.controller;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.JsonData;
import com.itlin.school.auth.bo.AddressBo;
import com.itlin.school.auth.convert.AddressDtoConvert;
import com.itlin.school.auth.dto.AddressReqDto;
import com.itlin.school.auth.entity.AddressDo;
import com.itlin.school.auth.service.AddressService;
import com.itlin.school.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/address/v1")
@Slf4j
@Api(tags = "地址模块")
public class AddressController {


    @Resource
    private AddressService addressService;

    @GetMapping("get/{address_id}")
    @ApiOperation("根据地址id获取地址信息")
    public JsonData get(@ApiParam(required = true, defaultValue = "1") @PathVariable Long address_id) {
        log.info("AddressController:get:addressId={}", address_id);
        try {
            AddressDo addressDo = addressService.queryById(address_id);
            return JsonData.buildSuccess(addressDo);
        } catch (Exception e) {
            log.error("AddressController:get:erro={}",e.getMessage());
            log.error(BizCodeEnum.SERVICE.getMessage());
            throw new BizException(BizCodeEnum.SERVICE);
        }
    }


    @PostMapping("save")
    @ApiOperation("保存地址信息")
    public JsonData save(@RequestBody AddressReqDto addressReqDto) {
        log.info("AddressController:save={}", addressReqDto);
        try {
            AddressBo addressBo = AddressDtoConvert.INSERT.AddressToBoConvert(addressReqDto);
            addressService.save(addressBo);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            log.error("AddressController:save:erro={}",e.getMessage());
            log.error(BizCodeEnum.SERVICE.getMessage());
            throw new BizException(BizCodeEnum.SERVICE);
        }
    }
}
