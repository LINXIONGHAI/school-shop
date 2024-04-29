package com.itlin.product.convert;

import com.itlin.product.bo.BannerResBo;
import com.itlin.product.bo.ProductResBo;
import com.itlin.product.dto.ProductReqDto;
import com.itlin.product.entity.Banner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductBoToDto {

    ProductBoToDto INSERT= Mappers.getMapper(ProductBoToDto.class);

    List<ProductReqDto>  ToList(List<ProductResBo> list);


}
