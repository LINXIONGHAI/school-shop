package com.itlin.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.itlin.**.dao")
@ComponentScan("com.itlin")
@EnableTransactionManagement
public class CouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class,args);
    }

}
