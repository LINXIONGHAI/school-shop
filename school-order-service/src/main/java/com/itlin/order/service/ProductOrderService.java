package com.itlin.order.service;

import com.itlin.common.util.JsonData;
import com.itlin.order.dto.ProductOrderSaveDto;
import com.itlin.order.entity.ProductOrder;

/**
 * (ProductOrder)表服务接口
 *
 * @author makejava
 * @since 2024-04-29 19:37:36
 */
public interface ProductOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductOrder queryById(Long id);


    /**
     * 新增数据
     *
     * @param productOrder 实例对象
     * @return 实例对象
     */
    ProductOrder insert(ProductOrder productOrder);

    /**
     * 修改数据
     *
     * @param productOrder 实例对象
     * @return 实例对象
     */
    ProductOrder update(ProductOrder productOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    JsonData createOrder(ProductOrderSaveDto saveDto);

}
