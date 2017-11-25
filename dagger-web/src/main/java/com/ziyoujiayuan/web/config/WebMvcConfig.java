package com.ziyoujiayuan.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ziyoujiayuan.web.interceptor.AuthorityHandlerInterceptor;
import com.ziyoujiayuan.web.interceptor.CrossDomainInterceptor;

/**
 * mvc-config
 * @Author wanghjbuf
 * @Date 2017年9月26日
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	AuthorityHandlerInterceptor authorityHandlerInterceptor;
	@Autowired
	CrossDomainInterceptor crossDomainInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {  
//        registry.addInterceptor(crossDomainInterceptor).addPathPatterns("/**");  
        registry.addInterceptor(authorityHandlerInterceptor).addPathPatterns("/**"); 
        
        super.addInterceptors(registry);
    } 

}
