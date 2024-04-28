package com.itlin.school.auth.service;

import com.itlin.school.auth.bo.AddressBo;
import com.itlin.school.auth.dto.UserResDto;
import com.itlin.school.auth.entity.AddressDo;

import java.util.List;

/**
 * 电商-公司收发货地址表(Address)表服务接口
 *
 * @author makejava
 * @since 2024-04-27 21:56:27
 */
public interface AddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AddressDo queryById(Long id);


    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    AddressDo insert(AddressDo address);

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    AddressDo update(AddressDo address);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    void save(AddressBo addressBo);

    List<AddressBo> getListByUserId();

    void delete(Long address_id);

}
