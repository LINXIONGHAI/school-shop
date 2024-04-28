package com.itlin.product.service.impl;

import com.itlin.product.entity.Product;
import com.itlin.product.dao.ProductDao;
import com.itlin.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2024-04-29 00:02:57
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Object id) {
        return this.productDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        this.productDao.insert(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productDao.update(product);
        return this.queryById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.productDao.deleteById(id) > 0;
    }
}
