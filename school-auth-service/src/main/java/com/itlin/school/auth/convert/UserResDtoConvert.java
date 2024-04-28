package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.dto.UserResDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResDtoConvert {
    UserResDtoConvert INSERT = Mappers.getMapper(UserResDtoConvert.class);
    UserResDto UserResDtooConvert(UserBo userBo);
}
