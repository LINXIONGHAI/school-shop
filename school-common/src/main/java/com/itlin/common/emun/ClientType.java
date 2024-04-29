package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum  ClientType {

    H5(1,"H5"),
    HTML(2,"HTML"),
    APP(3,"APP");
    private Integer type;

    private String desc;

    private static Map<Integer,ClientType> map=new HashMap<>();
    public static ClientType getByType(Integer clienType){
        for (ClientType clientType : ClientType.values()) {
            Integer type = clientType.getType();
            map.put(type,clientType);
        }
        return map.get(clienType);

    }


}
