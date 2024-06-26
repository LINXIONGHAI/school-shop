package com.itlin.school.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-04-27 21:56:29
 */
@Data
public class UserDo implements Serializable {
    private static final long serialVersionUID = -54421200782846399L;
    
    private Object id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
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
    private String mail;
    /**
     * 盐，用于个人敏感信息处理
     */
    private String secret;


}

