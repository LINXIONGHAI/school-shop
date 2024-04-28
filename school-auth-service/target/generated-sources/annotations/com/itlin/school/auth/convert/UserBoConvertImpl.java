package com.itlin.school.auth.convert;

import com.itlin.school.auth.bo.UserBo;
import com.itlin.school.auth.entity.UserDo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T16:46:55+0800",
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

    @Override
    public UserBo UserBoConvert(UserDo userDo) {
        if ( userDo == null ) {
            return null;
        }

        UserBo userBo = new UserBo();

        userBo.setId( userDo.getId() );
        userBo.setName( userDo.getName() );
        userBo.setPwd( userDo.getPwd() );
        userBo.setHeadImg( userDo.getHeadImg() );
        userBo.setSlogan( userDo.getSlogan() );
        userBo.setSex( userDo.getSex() );
        userBo.setPoints( userDo.getPoints() );
        userBo.setCreateTime( userDo.getCreateTime() );
        userBo.setMail( userDo.getMail() );
        userBo.setSecret( userDo.getSecret() );

        return userBo;
    }
}
