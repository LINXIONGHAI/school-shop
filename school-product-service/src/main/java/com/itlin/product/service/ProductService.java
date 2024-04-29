package com.itlin.product.service;

import com.itlin.common.feign.ProductRpc;
import com.itlin.common.feign.dto.ProduceRpcReqDto;
import com.itlin.common.util.JsonData;
import com.itlin.product.bo.ProductReqBo;
import com.itlin.product.bo.ProductResBo;
import com.itlin.product.dto.ProductReqDto;
import com.itlin.product.entity.Product;

import java.util.List;

/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2024-04-29 00:02:57
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Object id);


    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

    List<ProductResBo> list(ProductReqBo productReqBo);

    ProductResBo detail(int id);

    JsonData getListByIds(ProduceRpcReqDto produceRpcReqDto);
}
