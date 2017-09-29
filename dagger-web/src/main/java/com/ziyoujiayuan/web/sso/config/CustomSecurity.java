package com.ziyoujiayuan.web.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ziyoujiayuan.web.sso.service.OnlineUserService;

/**
 * spring-security用户登陆以及权限控制
 * @Author wanghjbuf
 * @Date 2017年9月25日
 */
@Configuration
public class CustomSecurity extends WebSecurityConfigurerAdapter{

	@Bean
	UserDetailsService onlineUserService() {
		return new OnlineUserService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(onlineUserService());
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests()
		            .anyRequest()
		            .authenticated()
		            .and()
		            .formLogin()
		            .loginPage("/login")
		            .failureUrl("/login?erro")
		            .permitAll()
		            .and()
		            .logout()
		            .permitAll();
	}
}

