package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.AddressBo;
import com.itlin.school.auth.dto.AddressReqDto;
import com.itlin.school.auth.entity.AddressDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressBoConvert {
    AddressBoConvert INSERT = Mappers.getMapper(AddressBoConvert.class);
    List<AddressBo> AddressToBoConvertList(List<AddressDo> list);


}
