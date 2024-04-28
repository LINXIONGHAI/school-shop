package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.dto.UserDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T12:51:56+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class UserDtoConvertImpl implements UserDtoConvert {

    @Override
    public UserBo UserBoConvert(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBo userBo = new UserBo();

        userBo.setId( userDto.getId() );
        userBo.setName( userDto.getName() );
        userBo.setPwd( userDto.getPwd() );
        userBo.setHeadImg( userDto.getHeadImg() );
        userBo.setSlogan( userDto.getSlogan() );
        userBo.setSex( userDto.getSex() );
        userBo.setPoints( userDto.getPoints() );
        userBo.setCreateTime( userDto.getCreateTime() );
        userBo.setMail( userDto.getMail() );
        userBo.setSecret( userDto.getSecret() );
        userBo.setEmailCode( userDto.getEmailCode() );

        return userBo;
    }
}
