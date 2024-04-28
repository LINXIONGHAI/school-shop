package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponPublishEnum {
    PUBLISH(1, "发布"),
    DRAFT(2, "草稿"),
    OFFLINE(3, "下线");

    private Integer code;
    private String dec;




}
