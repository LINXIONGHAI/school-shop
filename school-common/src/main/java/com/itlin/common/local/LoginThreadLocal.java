package com.itlin.common.local;

import com.itlin.common.entity.LoginUser;

public class LoginThreadLocal {

    public static ThreadLocal<LoginUser> threadLocal=new ThreadLocal<>();

    public static void set(LoginUser user){
        threadLocal.set(user);
    }

    public static LoginUser get(){
       return threadLocal.get();
    }



}
