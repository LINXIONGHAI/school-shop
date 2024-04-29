package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum  OrderType {

     DAILY(1,"普通单"),
     PROMOTION(1,"促销订单"),
             ;
     private Integer code;
     private String desc;

    private static Map<Integer,OrderType> map=new HashMap<>();
    public static OrderType getByType(Integer orderType){
        for (OrderType orderTypes : OrderType.values()) {
            Integer type = orderTypes.getCode();
            map.put(type,orderTypes);
        }
        return map.get(orderType);

    }

}
