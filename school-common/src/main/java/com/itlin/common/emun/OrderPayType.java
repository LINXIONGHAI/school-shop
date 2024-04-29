package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum OrderPayType {

    WX_PAY(1,"微信支付"),
    ALI_PAY(2,"支付宝支付"),
    ;
    private Integer code;
    private String desc;

    private static Map<Integer,OrderPayType> map=new HashMap<>();
    public static OrderPayType getByType(Integer payType){
        for (OrderPayType orderPayType : OrderPayType.values()) {
            Integer type = orderPayType.getCode();
            map.put(type,orderPayType);
        }
        return map.get(payType);

    }
}
