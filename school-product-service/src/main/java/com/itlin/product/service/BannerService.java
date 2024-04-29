package com.itlin.product.service;

import com.itlin.product.bo.BannerResBo;
import com.itlin.product.dto.BannerResDto;
import com.itlin.product.entity.Banner;

import java.util.List;

/**
 * (Banner)表服务接口
 *
 * @author makejava
 * @since 2024-04-29 00:02:56
 */
public interface BannerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Banner queryById(Integer id);


    /**
     * 新增数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    Banner insert(Banner banner);

    /**
     * 修改数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    Banner update(Banner banner);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<BannerResBo>  bannerList(int page, int pageSize);
}
