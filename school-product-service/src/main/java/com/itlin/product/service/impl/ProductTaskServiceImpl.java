package com.itlin.product.service.impl;

import com.itlin.common.emun.CouponLockState;
import com.itlin.common.entity.CartItemVo;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.feign.ProductRpc;
import com.itlin.common.feign.dto.ProduceRpcReqDto;
import com.itlin.common.feign.dto.ProductLockStockDto;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.JsonData;
import com.itlin.product.config.RabbitMqConfig;
import com.itlin.product.dao.ProductDao;
import com.itlin.product.entity.ProductTask;
import com.itlin.product.dao.ProductTaskDao;
import com.itlin.product.mq.StockLockMqDto;
import com.itlin.product.service.ProductTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (ProductTask)表服务实现类
 *
 * @author makejava
 * @since 2024-04-30 19:40:59
 */
@Service("productTaskService")
@Slf4j
public class ProductTaskServiceImpl implements ProductTaskService {
    @Resource
    private ProductTaskDao productTaskDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RabbitMqConfig rabbitMqConfig;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductTask queryById(Long id) {
        return this.productTaskDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param productTask 实例对象
     * @return 实例对象
     */
    @Override
    public ProductTask insert(ProductTask productTask) {
        this.productTaskDao.insert(productTask);
        return productTask;
    }

    /**
     * 修改数据
     *
     * @param productTask 实例对象
     * @return 实例对象
     */
    @Override
    public ProductTask update(ProductTask productTask) {
        this.productTaskDao.update(productTask);
        return this.queryById(productTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.productTaskDao.deleteById(id) > 0;
    }


    @Override
    @Transactional
    public JsonData lockStock(List<CartItemVo> cartItemVoList,String outTradeNo) {
        //判断库存和id是否存在，且库存足够
        int index = 0;
        for (int i = 0; i < cartItemVoList.size(); i++) {
            CartItemVo cartItemVo = cartItemVoList.get(i);
            //
            int count = productDao.queryByIdAndStok(cartItemVo.getProduct(), cartItemVo.getProductNum());
            index = index + count;

        }
        if (index != cartItemVoList.size()) {
            return JsonData.buildError("商品库存不足");
        }
        for (int i = 0; i < cartItemVoList.size(); i++) {
            CartItemVo cartItemVo = cartItemVoList.get(i);
            //锁定商品库存
            productDao.lock(cartItemVo.getProduct(),cartItemVo.getProductNum());
            ProductTask productTask = new ProductTask();
            productTask.setBuyNum(cartItemVo.getProductNum());
            productTask.setCreateTime(new Date());
            productTask.setLockState(CouponLockState.LOCK.name());
            productTask.setProductId(cartItemVo.getProduct().longValue());
            productTask.setProductName(cartItemVo.getProductTitle());
            productTask.setOutTradeNo(outTradeNo);
            productTaskDao.lock(productTask);

            //发送Mq TODO
            LoginUser loginUser = LoginThreadLocal.get();
            StockLockMqDto stockLockMqDto = new StockLockMqDto();
            stockLockMqDto.setUserId(String.valueOf(loginUser.getId()));
            stockLockMqDto.setOutTranceId(outTradeNo);
            stockLockMqDto.setTaskId(productTask.getId().toString());
            rabbitTemplate.convertAndSend(rabbitMqConfig.getEventExchange(),rabbitMqConfig.getStockReleaseDelayRoutingKey(),
                    stockLockMqDto);
        }
        log.info("商品库存锁定成功。。。。。。。");
        return JsonData.buildSuccess();
    }
}
