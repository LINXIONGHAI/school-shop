package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum  OrderStatues {
    NEW( 1,"未支付订单"),
    PAY(2,"已经支付订单"),
    CANCEL(3,"超时取消订单"),
    ;
    private Integer code;

    private String desc;


    private static Map<Integer,OrderStatues> map=new HashMap<>();
    public static OrderStatues getByType(Integer orderState){
        for (OrderStatues orderStatues : OrderStatues.values()) {
            Integer type = orderStatues.getCode();
            map.put(type,orderStatues);
        }
        return map.get(orderState);

    }



}
