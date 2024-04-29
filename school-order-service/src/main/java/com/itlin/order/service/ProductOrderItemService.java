package com.itlin.order.service;

import com.itlin.order.entity.ProductOrderItem;

/**
 * (ProductOrderItem)表服务接口
 *
 * @author makejava
 * @since 2024-04-29 19:38:43
 */
public interface ProductOrderItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductOrderItem queryById(Long id);


    /**
     * 新增数据
     *
     * @param productOrderItem 实例对象
     * @return 实例对象
     */
    ProductOrderItem insert(ProductOrderItem productOrderItem);

    /**
     * 修改数据
     *
     * @param productOrderItem 实例对象
     * @return 实例对象
     */
    ProductOrderItem update(ProductOrderItem productOrderItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
