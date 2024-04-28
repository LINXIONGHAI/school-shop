package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.dto.UserReqDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T16:46:55+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class UserDtoConvertImpl implements UserDtoConvert {

    @Override
    public UserBo UserBoConvert(UserReqDto userReqDto) {
        if ( userReqDto == null ) {
            return null;
        }

        UserBo userBo = new UserBo();

        userBo.setId( userReqDto.getId() );
        userBo.setName( userReqDto.getName() );
        userBo.setPwd( userReqDto.getPwd() );
        userBo.setHeadImg( userReqDto.getHeadImg() );
        userBo.setSlogan( userReqDto.getSlogan() );
        userBo.setSex( userReqDto.getSex() );
        userBo.setPoints( userReqDto.getPoints() );
        userBo.setCreateTime( userReqDto.getCreateTime() );
        userBo.setMail( userReqDto.getMail() );
        userBo.setSecret( userReqDto.getSecret() );
        userBo.setEmailCode( userReqDto.getEmailCode() );

        return userBo;
    }

    @Override
    public UserReqDto UserDtooConvert(UserBo userBo) {
        if ( userBo == null ) {
            return null;
        }

        UserReqDto userReqDto = new UserReqDto();

        userReqDto.setId( userBo.getId() );
        userReqDto.setName( userBo.getName() );
        userReqDto.setPwd( userBo.getPwd() );
        userReqDto.setHeadImg( userBo.getHeadImg() );
        userReqDto.setSlogan( userBo.getSlogan() );
        userReqDto.setSex( userBo.getSex() );
        userReqDto.setPoints( userBo.getPoints() );
        userReqDto.setCreateTime( userBo.getCreateTime() );
        userReqDto.setMail( userBo.getMail() );
        userReqDto.setSecret( userBo.getSecret() );
        userReqDto.setEmailCode( userBo.getEmailCode() );

        return userReqDto;
    }
}
