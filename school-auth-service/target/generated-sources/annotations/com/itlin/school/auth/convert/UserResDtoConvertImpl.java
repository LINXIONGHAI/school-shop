package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.dto.UserResDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T20:53:30+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class UserResDtoConvertImpl implements UserResDtoConvert {

    @Override
    public UserResDto UserResDtooConvert(UserBo userBo) {
        if ( userBo == null ) {
            return null;
        }

        UserResDto userResDto = new UserResDto();

        userResDto.setId( userBo.getId() );
        userResDto.setName( userBo.getName() );
        userResDto.setHeadImg( userBo.getHeadImg() );
        userResDto.setSlogan( userBo.getSlogan() );
        userResDto.setSex( userBo.getSex() );
        userResDto.setPoints( userBo.getPoints() );
        userResDto.setCreateTime( userBo.getCreateTime() );
        userResDto.setMail( userBo.getMail() );
        userResDto.setEmailCode( userBo.getEmailCode() );

        return userResDto;
    }
}
