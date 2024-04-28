package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoConvert {
    UserDtoConvert INSERT = Mappers.getMapper(UserDtoConvert.class);
    UserBo UserBoConvert(UserDto userDto);
}
