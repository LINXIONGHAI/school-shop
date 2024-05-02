package com.itlin.order.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.itlin.common.emun.ClientType;
import com.itlin.common.emun.OrderPayType;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.JsonData;
import com.itlin.order.dto.ProductOrderSaveDto;
import com.itlin.order.service.ProductOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/order/v1")
@Slf4j
public class ProductOrderController {


    @Resource
    private ProductOrderService productOrderService;


    @PostMapping("/save")
    public JsonData createOrder(@RequestBody ProductOrderSaveDto saveDto, HttpServletResponse response) {

        //创建订单
        JsonData jsonData = productOrderService.createOrder(saveDto);

        if(jsonData.getCode()==0){
            ClientType byType = ClientType.getByType(saveDto.getClientType());
            if (byType == null) {
                throw new BizException(500,"客户端不支持");
            }
            switch (byType.name()){
                case "HTML":{
                    if(OrderPayType.getByType(saveDto.getPayType()).name()==OrderPayType.ALI_PAY.name()){
                        writeHtnl(jsonData,response);
                    }
                }


            }


        }

            return JsonData.buildError("创建订房失败");

    }

    /**
     * html写出数据
     *
     * @param jsonData
     * @param response
     */
    private void writeHtnl(JsonData jsonData, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF8");
        try {
            response.getWriter().write(jsonData.getData().toString());
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("创建订单失败");
        }


    }

    @ApiOperation("RPC")
    @GetMapping("/getByOutTraneId/{outTraneId}")
    public JsonData getByOutTrane(@PathVariable("outTraneId") String outTraneId){
        return productOrderService.getByOutTrane(outTraneId);


    }


}
