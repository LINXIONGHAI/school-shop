package com.itlin.product.convert;

import com.itlin.common.feign.ProductRpc;
import com.itlin.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RpcConvert {
    RpcConvert RPC = Mappers.getMapper(RpcConvert.class);
    ProductRpc ProductToRpc(Product product);


    List<ProductRpc> ProductToRpcList(List<Product> list);
}
