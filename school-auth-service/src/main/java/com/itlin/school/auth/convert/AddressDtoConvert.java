package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.AddressBo;
import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.dto.AddressReqDto;
import com.itlin.school.auth.dto.AddressResDto;
import com.itlin.school.auth.dto.UserReqDto;
import com.itlin.school.auth.entity.AddressDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressDtoConvert {
    AddressDtoConvert INSERT = Mappers.getMapper(AddressDtoConvert.class);
    AddressBo AddressToBoConvert(AddressReqDto addressReqDto);
    AddressDo AddressToBoConvert(AddressBo addressBo);
    List<AddressResDto> AddressToResConvertList(List<AddressBo> addressBos);



}
