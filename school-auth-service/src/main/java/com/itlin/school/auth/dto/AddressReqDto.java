package com.itlin.school.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 电商-公司收发货地址表(Address)实体类
 *
 * @author makejava
 * @since 2024-04-27 21:56:26
 */
@Data
public class AddressReqDto implements Serializable {
    private static final long serialVersionUID = 172017262177519890L;
    
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 是否默认收货地址：0->否；1->是
     */
    private Integer defaultStatus;
    /**
     * 收发货人姓名
     */
    private String receiveName;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 省/直辖市
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 详细地址
     */
    private String detailAddress;
    
    private Date createTime;



}

