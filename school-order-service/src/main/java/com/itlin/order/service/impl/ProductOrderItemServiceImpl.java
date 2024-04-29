package com.itlin.order.service.impl;

import com.itlin.order.entity.ProductOrderItem;
import com.itlin.order.dao.ProductOrderItemDao;
import com.itlin.order.service.ProductOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (ProductOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2024-04-29 19:38:43
 */
@Service("productOrderItemService")
public class ProductOrderItemServiceImpl implements ProductOrderItemService {
    @Resource
    private ProductOrderItemDao productOrderItemDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductOrderItem queryById(Long id) {
        return this.productOrderItemDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param productOrderItem 实例对象
     * @return 实例对象
     */
    @Override
    public ProductOrderItem insert(ProductOrderItem productOrderItem) {
        this.productOrderItemDao.insert(productOrderItem);
        return productOrderItem;
    }

    /**
     * 修改数据
     *
     * @param productOrderItem 实例对象
     * @return 实例对象
     */
    @Override
    public ProductOrderItem update(ProductOrderItem productOrderItem) {
        this.productOrderItemDao.update(productOrderItem);
        return this.queryById(productOrderItem.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.productOrderItemDao.deleteById(id) > 0;
    }
}
