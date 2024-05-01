package com.itlin.coupon;

import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = CouponApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestApplication {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        rabbitTemplate.convertAndSend("test.event.exchange", "coupon.release.delay.routing.key", "nihao");
        log.info("SUCCESS");
    }


}
