package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CouponLockState {
    LOCK(1,"锁定"),
    FINISH(2,"完成"),
    CANCEL(3,"取消"),;

    private Integer code;
    private String desc;

}
