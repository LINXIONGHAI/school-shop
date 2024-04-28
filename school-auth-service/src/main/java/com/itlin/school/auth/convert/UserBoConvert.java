package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.entity.UserDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserBoConvert {

    UserBoConvert INSERT = Mappers.getMapper(UserBoConvert.class);
    UserDo UserDoConvert(UserBo userBo);
    UserBo  UserBoConvert(UserDo userDo);
}
