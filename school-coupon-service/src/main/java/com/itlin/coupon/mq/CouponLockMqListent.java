package com.itlin.coupon.mq;

import com.itlin.coupon.service.CouponRecordService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 优惠卷监听是否释放优惠卷
 */
@Slf4j
@Component
@RabbitListener(queues = "${mqconfig.coupon_release_queue}")
public class CouponLockMqListent {

    @Resource
    private CouponRecordService couponRecordService;

    //CouponMQLockReq couponMQLockReq
    @RabbitHandler
    public void CouponUnLockListener(CouponMQLockReq couponMQLockReq, Message message, Channel channel) throws IOException {
        log.info("CouponUnLockListener监听到消息：{}",couponMQLockReq);
        long tag = message.getMessageProperties().getDeliveryTag();
        Boolean flag = couponRecordService.unlock(couponMQLockReq);
        try {

        if (flag){
                channel.basicAck(tag, false);
            } else {
            log.error("优惠卷释放失败:{}",couponMQLockReq);
            channel.basicReject(tag,true);
        }
        }catch (IOException e) {
            log.error("优惠卷释放失败:{}",couponMQLockReq);
            channel.basicReject(tag,true);
        }




    }

}
