package com.itlin.product.convert;

import com.itlin.product.bo.ProductResBo;
import com.itlin.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductToBo {

    ProductToBo INSERT= Mappers.getMapper(ProductToBo.class);

    List<ProductResBo>  ToList(List<Product> list);


    ProductResBo ToBo(Product product);
}
