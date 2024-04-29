package com.itlin.order.service;

import com.itlin.order.entity.ProductOrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (ProductOrderItem)表服务接口
 *
 * @author makejava
 * @since 2024-04-29 19:37:38
 */
public interface ProductOrderItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductOrderItem queryById(Object id);

    /**
     * 分页查询
     *
     * @param productOrderItem 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ProductOrderItem> queryByPage(ProductOrderItem productOrderItem, PageRequest pageRequest);

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
    boolean deleteById(Object id);

}
