package com.itlin.school.auth.convert;

import com.itlin.common.entity.LoginUser;
import com.itlin.school.auth.entity.UserDo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-29T01:11:27+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class LoginUserConvertImpl implements LoginUserConvert {

    @Override
    public LoginUser UserLoginConvert(UserDo userBo) {
        if ( userBo == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        loginUser.setId( userBo.getId() );
        loginUser.setName( userBo.getName() );
        loginUser.setPwd( userBo.getPwd() );
        loginUser.setHeadImg( userBo.getHeadImg() );
        loginUser.setSlogan( userBo.getSlogan() );
        loginUser.setSex( userBo.getSex() );
        loginUser.setPoints( userBo.getPoints() );
        loginUser.setCreateTime( userBo.getCreateTime() );
        loginUser.setMail( userBo.getMail() );
        loginUser.setSecret( userBo.getSecret() );

        return loginUser;
    }
}
