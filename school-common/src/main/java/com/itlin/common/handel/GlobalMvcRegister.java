package com.itlin.common.handel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class GlobalMvcRegister implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercept())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/v1/login", "/api/user/v1/register", "/api/notif/v1/getCapter"
                        , "/api/notif/v1/sendEmail","/api/couponRecord/v1/new_user_coupon"
                ,"/api/product/v1/list","/api/banner/v1/bannerList")
        ;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().serializationInclusion(JsonInclude.Include.NON_NULL);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }
}
