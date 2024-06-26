package com.itlin.common.emun;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description 状态码定义约束，共6位数，前三位代表服务，后4位代表接口
 * 比如 商品服务210,购物车是220、用户服务230，403代表权限
 **/
@Getter
public enum BizCodeEnum {

    /**
     * 通用操作码
     */
    OPS_REPEAT(110001, "重复操作"),
    /**
     * 验证码
     */
    CODE_TO_ERROR(240001, "接收号码不合规"),

    CODE_LIMITED(240002, "验证码发送过快"),
    CODE_ERROR(240003, "验证码错误"),
    CODE_CAPTCHA(240101, "图形验证码错误"),
    /**
     * 账号
     */
    ACCOUNT_REPEAT(250001, "账号已经存在"),

    ACCOUNT_UNREGISTER(250002, "账号不存在"),
    ACCOUNT_PWD_ERROR(250003, "账号或者密码错误"),
    ACCOUNT_EXPIRE(250004,"用户已经过期，重新登录"),
    NOT_LOGIN(250004,"未登录"),
    /**
     * 服务异常
     */
    SERVICE(240003, "服务超时，稍后重试"),

    FILE_ERRO(240004, "文件上传失败"),

    ADDRESS_INSERT_ERROR(2400009,"删除地址失败"),
    ADDRESS_ERRO(2400010,"默认地址不能删除"),


    COUPON_NO_PUBLISH(1562,"优惠卷未发布"),
    COUNP_RECORD_ERROR(1563,"领取上限"),
    COUPON_NO_NULL(1564,"优惠卷已被抢光"),
    COUPON_NO_DATE(1565,"优惠卷已过期"),
    COUPON_NEW_ERRO(1656,"无新人优惠"),
    COUPONRECOUD_NO_NULL(500,"优惠卷不存在"),


    PRODUCT_NO_NULL(300001, "没有找到这个商品信息"),



    CART_NO_PRODUCT(40005,"商品不存在"),

    RPC_ERRO(500,"rpc调用失败"),
    ;

    private BizCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;




}