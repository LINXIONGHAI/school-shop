package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum CouponCategoryEnum {

    PROMOTION(1, "促销劵"),
    TASK(2, "任务卷"),
    NEW_USER(3, "注册赠券");

    private Integer code;

    private String dec;

    static Map<Integer, CouponCategoryEnum> map = new HashMap<>();


    public static CouponCategoryEnum getByCode(Integer code) {
      for (CouponCategoryEnum couponCategoryEnum:CouponCategoryEnum.values()){
          map.put(couponCategoryEnum.getCode(),couponCategoryEnum);
      }
      return map.get(code);
    }

}
