package com.itlin.product.convert;

import com.itlin.product.bo.ProductReqBo;
import com.itlin.product.bo.ProductResBo;
import com.itlin.product.dto.ProductReqDto;
import com.itlin.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductDtoToBo {

    ProductDtoToBo INSERT= Mappers.getMapper(ProductDtoToBo.class);

    ProductReqBo ToReqBo(ProductReqDto productReqBo);


}
