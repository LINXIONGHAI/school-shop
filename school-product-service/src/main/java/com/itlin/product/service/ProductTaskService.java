package com.itlin.product.service;

import com.itlin.common.entity.CartItemVo;
import com.itlin.common.feign.ProductRpc;
import com.itlin.common.feign.dto.ProductLockStockDto;
import com.itlin.common.util.JsonData;
import com.itlin.product.entity.ProductTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (ProductTask)表服务接口
 *
 * @author makejava
 * @since 2024-04-30 19:40:59
 */
public interface ProductTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductTask queryById(Long id);


    /**
     * 新增数据
     *
     * @param productTask 实例对象
     * @return 实例对象
     */
    ProductTask insert(ProductTask productTask);

    /**
     * 修改数据
     *
     * @param productTask 实例对象
     * @return 实例对象
     */
    ProductTask update(ProductTask productTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    JsonData lockStock(List<CartItemVo> cartItemVoList,String outTradeNo);

}
