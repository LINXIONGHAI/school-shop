package com.itlin.school.auth.handel;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.entity.LoginUser;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.local.LoginThreadLocal;
import com.itlin.common.util.CommonUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("itlin.token");
        if(header==null){
            throw new BizException(BizCodeEnum.ACCOUNT_EXPIRE);
        }
        Claims claims = CommonUtil.checkJWT(header);
        if(claims==null){
            throw new BizException(BizCodeEnum.ACCOUNT_EXPIRE);
        }
        Object id = claims.get("id");
        Object mail = claims.get("mail");
        Object head_img = claims.get("head_img");
        LoginUser loginUser=new LoginUser();
        loginUser.setHeadImg(head_img.toString());
        loginUser.setId(id);
        loginUser.setMail(mail.toString());
        LoginThreadLocal.set(loginUser);
        return true;
    }
}
