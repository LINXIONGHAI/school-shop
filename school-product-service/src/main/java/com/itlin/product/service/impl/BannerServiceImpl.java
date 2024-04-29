package com.itlin.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.itlin.product.bo.BannerResBo;
import com.itlin.product.convert.BannerBoToDto;
import com.itlin.product.dto.BannerResDto;
import com.itlin.product.entity.Banner;
import com.itlin.product.dao.BannerDao;
import com.itlin.product.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Banner)表服务实现类
 *
 * @author makejava
 * @since 2024-04-29 00:02:57
 */
@Service("bannerService")
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDao bannerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Banner queryById(Integer id) {
        return this.bannerDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    @Override
    public Banner insert(Banner banner) {
        this.bannerDao.insert(banner);
        return banner;
    }

    /**
     * 修改数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    @Override
    public Banner update(Banner banner) {
        this.bannerDao.update(banner);
        return this.queryById(banner.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.bannerDao.deleteById(id) > 0;
    }

    @Override
    public List<BannerResBo> bannerList(int page, int pageSize) {

        PageHelper.startPage(page,pageSize);
        List<Banner> list=bannerDao.bannerList();
        List<BannerResBo> bannerResBos = BannerBoToDto.INSERT.ToList(list);
        return bannerResBos;


    }
}
