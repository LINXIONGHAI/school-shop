package com.itlin.common.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHelpConfig {

    @Bean
    public PageHelper pageHelper(){
        return new PageHelper();
    }

}
