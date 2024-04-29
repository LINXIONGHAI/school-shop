package com.itlin.order.service.impl;

import com.itlin.order.entity.ProductOrderItem;
import com.itlin.order.dao.ProductOrderItemDao;
import com.itlin.order.service.ProductOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (ProductOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2024-04-29 19:37:38
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
    public ProductOrderItem queryById(Object id) {
        return this.productOrderItemDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param productOrderItem 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ProductOrderItem> queryByPage(ProductOrderItem productOrderItem, PageRequest pageRequest) {
        long total = this.productOrderItemDao.count(productOrderItem);
        return new PageImpl<>(this.productOrderItemDao.queryAllByLimit(productOrderItem, pageRequest), pageRequest, total);
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
    public boolean deleteById(Object id) {
        return this.productOrderItemDao.deleteById(id) > 0;
    }
}
