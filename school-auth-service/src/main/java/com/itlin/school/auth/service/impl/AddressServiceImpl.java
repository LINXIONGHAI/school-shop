package com.itlin.school.auth.service.impl;

import com.itlin.common.entity.LoginUser;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.school.auth.bo.AddressBo;
import com.itlin.school.auth.convert.AddressBoConvert;
import com.itlin.school.auth.convert.AddressDtoConvert;
import com.itlin.school.auth.dao.UserDao;
import com.itlin.school.auth.dto.UserResDto;
import com.itlin.school.auth.entity.AddressDo;
import com.itlin.school.auth.dao.AddressDao;
import com.itlin.school.auth.entity.UserDo;
import com.itlin.school.auth.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Timer;

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

    LoginUser loginUser=null;
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

    @Transactional
    @Override
    public void save(AddressBo addressBo) {
        LoginUser loginUser = LoginThreadLocal.get();
        Integer defaultStatus = addressBo.getDefaultStatus();
            //默认
            addressBo.setUserId(Long.parseLong(String.valueOf(loginUser.getId())));
            AddressDo addressDo = AddressDtoConvert.INSERT.AddressToBoConvert(addressBo);
            if(addressBo.getDefaultStatus()==1){
                addressDao.updattByUserId(addressDo);
                addressDao.insert(addressDo);
                return;
            }
            addressDao.insert(addressDo);




    }


    @Override
    public List<AddressBo> getListByUserId() {
        LoginUser loginUser = LoginThreadLocal.get();
        AddressDo addressDo = new AddressDo();
        addressDo.setUserId(Long.parseLong(String.valueOf(loginUser.getId())));
        List<AddressDo> list = query(addressDo);
        List<AddressBo> addressBos = AddressBoConvert.INSERT.AddressToBoConvertList(list);
        return addressBos;


    }

    private List<AddressDo> query(AddressDo addressDo) {

       return addressDao.query(addressDo);

    }
}
