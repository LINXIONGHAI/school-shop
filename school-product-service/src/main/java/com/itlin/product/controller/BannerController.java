package com.itlin.product.controller;

import com.itlin.common.util.JsonData;
import com.itlin.product.bo.BannerResBo;
import com.itlin.product.dto.BannerResDto;
import com.itlin.product.service.BannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/banner/v1")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @ApiOperation("查询主页轮播图")
    @GetMapping("bannerList")
    public JsonData bannerList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "3") int pageSize){
        List<BannerResBo>  list = bannerService.bannerList(page, pageSize);
        return JsonData.buildSuccess(list);


    }

}
