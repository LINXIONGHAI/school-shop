package com.itlin.product.mq;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
@RabbitListener(queues = "${mqconfig.stock_release_queue}")
public class ProductOrderUnLockListener {

//    @Resource
//    private

    @RabbitHandler
    public void productOrderUnLockListener(StockLockMqDto stockLockMqDto, Message message, Channel channel){
        log.info("接受到消息:{}",stockLockMqDto);

    }
}
