package com.itlin.school.auth.service.impl;

import com.itlin.school.auth.entity.AddressDo;
import com.itlin.school.auth.dao.AddressDao;
import com.itlin.school.auth.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 电商-公司收发货地址表(Address)表服务实现类
 *
 * @author makejava
 * @since 2024-04-27 21:56:28
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressDao addressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AddressDo queryById(Long id) {
        return this.addressDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public AddressDo insert(AddressDo address) {
        this.addressDao.insert(address);
        return address;
    }

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public AddressDo update(AddressDo address) {
        this.addressDao.update(address);
        return this.queryById(address.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.addressDao.deleteById(id) > 0;
    }
}
