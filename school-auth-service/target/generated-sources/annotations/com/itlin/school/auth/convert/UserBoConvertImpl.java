package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.entity.UserDo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T12:51:56+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class UserBoConvertImpl implements UserBoConvert {

    @Override
    public UserDo UserDoConvert(UserBo userBo) {
        if ( userBo == null ) {
            return null;
        }

        UserDo userDo = new UserDo();

        userDo.setId( userBo.getId() );
        userDo.setName( userBo.getName() );
        userDo.setPwd( userBo.getPwd() );
        userDo.setHeadImg( userBo.getHeadImg() );
        userDo.setSlogan( userBo.getSlogan() );
        userDo.setSex( userBo.getSex() );
        userDo.setPoints( userBo.getPoints() );
        userDo.setCreateTime( userBo.getCreateTime() );
        userDo.setMail( userBo.getMail() );
        userDo.setSecret( userBo.getSecret() );

        return userDo;
    }
}
