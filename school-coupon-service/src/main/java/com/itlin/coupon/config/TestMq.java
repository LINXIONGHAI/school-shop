package com.itlin.coupon.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestMq {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/testmq")
    public String test(){
        rabbitTemplate.convertAndSend("coupon.event.exchange", "coupon.release.delay.routing.key", "haihaihai");
        return "SUCCESS";
    }
}
