package com.itlin.school.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-04-27 21:56:29
 */
@Data
public class UserReqDto implements Serializable {
    private static final long serialVersionUID = -54421200782846399L;

    private Object id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String pwd;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 用户签名
     */
    private String slogan;
    /**
     * 0表示女，1表示男
     */
    private Integer sex;
    /**
     * 积分
     */
    private Integer points;

    private Date createTime;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    private String mail;
    /**
     * 盐，用于个人敏感信息处理
     */
    private String secret;


    /**
     * 邮箱验证码
     */
//    @NotNull(message = "验证码不能为空")
    private String emailCode;


}

