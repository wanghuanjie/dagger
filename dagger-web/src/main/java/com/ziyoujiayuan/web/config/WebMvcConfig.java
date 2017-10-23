package com.ziyoujiayuan.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ziyoujiayuan.web.interceptor.AuthorityHandlerInterceptor;

/**
 * mvc-config
 * @Author wanghjbuf
 * @Date 2017年9月26日
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	AuthorityHandlerInterceptor authorityHandlerInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {  
        registry.addInterceptor(authorityHandlerInterceptor).addPathPatterns("/**");  
    } 

}
