package com.itlin.coupon.config;


import com.netflix.loadbalancer.PingUrl;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Data
public class RabbitMqConfig {


    /**
     * 交换机
     */
    @Value("${mqconfig.coupon_event_exchange}")
    private String eventExchange;


    /**
     * 第一个队列延迟队列，
     */
    @Value("${mqconfig.coupon_release_delay_queue}")
    private String couponReleaseDelayQueue;

    /**
     * 第一个队列的路由key
     * 进入队列的路由key
     */
    @Value("${mqconfig.coupon_release_delay_routing_key}")
    private String couponReleaseDelayRoutingKey;


    /**
     * 第二个队列，被监听恢复库存的队列
     */
    @Value("${mqconfig.coupon_release_queue}")
    private String couponReleaseQueue;

    /**
     * 第二个队列的路由key
     *
     * 即进入死信队列的路由key
     */
    @Value("${mqconfig.coupon_release_routing_key}")
    private String couponReleaseRoutingKey;

    /**
     * 过期时间
     */
    @Value("${mqconfig.ttl}")
    private Integer ttl;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();

    }

    /**
     * 交换机
     * @return
     */
    @Bean
    public Exchange counponExchange(){
        return new  TopicExchange(eventExchange,true,false);

    }

    /**
     * 创建延时队列
     * @return
     */
    @Bean
    public Queue couponQueueDelay(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-message-ttl",ttl);
        args.put("x-dead-letter-routing-key",couponReleaseDelayRoutingKey);
        args.put("x-dead-letter-exchange",eventExchange);
        return new Queue(couponReleaseDelayQueue,true,false,false);
    }

    /**
     * 创建死信队列
     * @return
     */
    @Bean
    public Queue couponQueue(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-message-ttl",ttl);
        args.put("x-dead-letter-routing-key",couponReleaseRoutingKey);
        args.put("x-dead-letter-exchange",eventExchange);
        return new Queue(couponReleaseQueue,true,false,false,args);
    }


    /**
     * 死信队列绑定交换机
     * @return
     */
    @Bean
    public Binding bindingRelease(){
        return new Binding(couponReleaseQueue, Binding.DestinationType.QUEUE,eventExchange,
                couponReleaseRoutingKey,null);

    }

    /**
     * 延迟队绑定
     */
    @Bean
    public Binding bindingReleaseDelay(){
        return new Binding(couponReleaseDelayQueue, Binding.DestinationType.QUEUE,eventExchange,
                couponReleaseDelayRoutingKey,null);
    }


}
