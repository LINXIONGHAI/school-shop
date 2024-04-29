package com.itlin.order.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itlin.common.entity.CartItemVo;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.feign.ProductRpc;
import com.itlin.common.feign.dto.ProduceRpcReqDto;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.JsonData;
import com.itlin.order.dto.ProductOrderSaveDto;
import com.itlin.order.entity.ProductOrder;
import com.itlin.order.dao.ProductOrderDao;
import com.itlin.order.feign.ProductServiceRpc;
import com.itlin.order.service.ProductOrderService;
import com.itlin.redis.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * (ProductOrder)表服务实现类
 *
 * @author makejava
 * @since 2024-04-29 19:37:37
 */
@Service("productOrderService")
public class ProductOrderServiceImpl implements ProductOrderService {
    @Resource
    private ProductOrderDao productOrderDao;

    @Resource
    private ProductServiceRpc productServiceRpc;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductOrder queryById(Long id) {
        return this.productOrderDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param productOrder 实例对象
     * @return 实例对象
     */
    @Override
    public ProductOrder insert(ProductOrder productOrder) {
        this.productOrderDao.insert(productOrder);
        return productOrder;
    }

    /**
     * 修改数据
     *
     * @param productOrder 实例对象
     * @return 实例对象
     */
    @Override
    public ProductOrder update(ProductOrder productOrder) {
        this.productOrderDao.update(productOrder);
        return this.queryById(productOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.productOrderDao.deleteById(id) > 0;
    }


    @Override
    public JsonData createOrder(ProductOrderSaveDto saveDto) {

        List<Integer> prductIds = saveDto.getPrductIds();
        ProduceRpcReqDto produceRpcReqDto = new ProduceRpcReqDto();
        produceRpcReqDto.setIds(prductIds);
        JsonData listByIds = productServiceRpc.getListByIds(produceRpcReqDto);
        Object data = listByIds.getData();
        String prouductStr = "";
        Gson gson = new Gson();
        if (data == null) {
            throw new BizException(500, "无商品");
        }
        prouductStr = data.toString();
        //数据库商品的数据
        Type type = new TypeToken<List<ProductRpc>>() {
        }.getType();
        List<ProductRpc> list = gson.fromJson(prouductStr, type);


        BoundHashOperations boundHashOperations = redisUtil.getBoundHashOperations(myCat());
        if (boundHashOperations == null) {
            throw new BizException(500, "请添加购物车");
        }
        //redis选中的商品信息
        List<CartItemVo> redisList = new ArrayList<>();
        //手机
        for (int i = 0; i < prductIds.size(); i++) {
            Integer integer = prductIds.get(i);
            String redisCatVal = boundHashOperations.get(String.valueOf(integer)).toString();
            CartItemVo cartItemVo = gson.fromJson(redisCatVal, CartItemVo.class);
            redisList.add(cartItemVo);
        }
        BigDecimal bigDecimal = getAmountAll(list, redisList);
        //发起支付

        return JsonData.buildSuccess("<h1>你好</h1>");


    }

    /**
     * 验证db和redis的数据金额
     *
     * @param dbList
     * @param redisList
     * @return
     */
    private BigDecimal getAmountAll(List<ProductRpc> dbList, List<CartItemVo> redisList) {

        BigDecimal sum = new BigDecimal("0");

        for (int i = 0; i < redisList.size(); i++) {
            CartItemVo cartItemVo = redisList.get(i);
            for (int i1 = 0; i1 < dbList.size(); i1++) {
                ProductRpc productRpc = dbList.get(i1);
                if (cartItemVo.getProduct().toString().equals(productRpc.getId().toString())) {
                    if (cartItemVo.getProductAmout().compareTo(new BigDecimal(productRpc.getPrice())) != 0) {
                        cartItemVo.setProductAmout(new BigDecimal(productRpc.getPrice()));
                        cartItemVo.setProductTilalAmount();
                        redisList.set(i, cartItemVo);
                    }

                }
            }


        }
        for (int i = 0; i < redisList.size(); i++) {
            CartItemVo cartItemVo = redisList.get(i);
            sum = sum.add(cartItemVo.getProductTilalAmount());
        }


        return sum;
    }

    /**
     * 获取购物车key
     */
    public String myCat() {
        LoginUser loginUser = LoginThreadLocal.get();
        return redisUtil.buildKey("cart", "shop", String.valueOf(loginUser.getId()));
    }
}
