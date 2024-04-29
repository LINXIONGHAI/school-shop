package com.itlin.product.controller;

import com.itlin.common.util.JsonData;
import com.itlin.product.bo.BannerResBo;
import com.itlin.product.bo.ProductReqBo;
import com.itlin.product.bo.ProductResBo;
import com.itlin.product.convert.ProductBoToDto;
import com.itlin.product.convert.ProductDtoToBo;
import com.itlin.product.dto.ProductReqDto;
import com.itlin.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    @Resource
    private ProductService productService;

    @ApiOperation("查询主页轮播图")
    @GetMapping("list")
    public JsonData list(ProductReqDto productReqDto){
        ProductReqBo productReqBo = ProductDtoToBo.INSERT.ToReqBo(productReqDto);
        List<ProductResBo> productResBo=productService.list(productReqBo);
        List<ProductReqDto> productReqDtos = ProductBoToDto.INSERT.ToList(productResBo);
        return JsonData.buildSuccess(productReqDtos);


    }
}
