package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponStatus {

    NEW(1, "可用"),
    USED(2, "已使用"),
    EXPIRED(3, "过期");

    private Integer code;
    private String dec;
}
