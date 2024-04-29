package com.itlin.product.convert;

import com.itlin.product.bo.BannerResBo;
import com.itlin.product.entity.Banner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerBoToDto {

    BannerBoToDto INSERT= Mappers.getMapper(BannerBoToDto.class);

    List<BannerResBo>  ToList(List<Banner> list);


}
