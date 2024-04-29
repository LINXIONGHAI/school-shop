package com.itlin.order.service.impl;

import com.itlin.order.entity.ProductOrder;
import com.itlin.order.dao.ProductOrderDao;
import com.itlin.order.service.ProductOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (ProductOrder)表服务实现类
 *
 * @author makejava
 * @since 2024-04-29 19:37:37
 */
@Service("productOrderService")
public class ProductOrderServiceImpl implements ProductOrderService {
    @Resource
    private ProductOrderDao productOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductOrder queryById(Long id) {
        return this.productOrderDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param productOrder 实例对象
     * @return 实例对象
     */
    @Override
    public ProductOrder insert(ProductOrder productOrder) {
        this.productOrderDao.insert(productOrder);
        return productOrder;
    }

    /**
     * 修改数据
     *
     * @param productOrder 实例对象
     * @return 实例对象
     */
    @Override
    public ProductOrder update(ProductOrder productOrder) {
        this.productOrderDao.update(productOrder);
        return this.queryById(productOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.productOrderDao.deleteById(id) > 0;
    }
}
