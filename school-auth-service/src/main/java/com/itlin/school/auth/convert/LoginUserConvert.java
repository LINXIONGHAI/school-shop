package com.itlin.school.auth.convert;

import com.itlin.common.entity.LoginUser;
import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.entity.UserDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginUserConvert {
    LoginUserConvert INSERT = Mappers.getMapper(LoginUserConvert.class);
    LoginUser UserLoginConvert(UserDo userBo);

}
