package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponCategoryEnum {

    PROMOTION(1, "促销劵"),
    TASK(2, "任务卷"),
    NEW_USER(3, "注册赠券");

    private Integer code;
    private String dec;




}
