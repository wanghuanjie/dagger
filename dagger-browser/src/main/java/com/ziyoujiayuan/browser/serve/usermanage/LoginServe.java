package com.ziyoujiayuan.browser.serve.usermanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.LoginService;
import com.ziyoujiayuan.base.exception.AppException;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录/登出(注销)相关服务
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Slf4j
@Service("com.ziyoujiayuan.browser.serve.usermanage.LoginServe")
public class LoginServe {

	@Qualifier("com.ziyoujiayuan.service.usermanage.LoginServiceImpl")
	@Autowired
	LoginService loginService;
	
	/**
	 * 登录相关操作
	 * @throws AppException
	 */
	public void login() throws AppException{
		log.info("login");
		loginService.doLogin();
	}
	
	/**
	 * 登出相关操作
	 * @throws AppException
	 */
	public void logout() throws AppException {
		log.info("logout");
		loginService.dologout();
	}
}
