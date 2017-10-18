package com.ziyoujiayuan.browser.serve.usermanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.RegisterService;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.beans.register.RegisterRequestParam;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 注册服务组合
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
@Slf4j
@Service("com.ziyoujiayuan.browser.serve.usermanage.RegisterServe")
public class RegisterServe {
	
	@Qualifier("com.ziyoujiayuan.service.usermanage.RegisterServiceImpl")
	@Autowired
	RegisterService registerService;
	
	public void registerUserInfo(RegisterRequestParam registerRequestParam) throws AppException{
		log.info("注册服务应用开始");
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setName(registerRequestParam.getName());
		userInfoBean.setPassword(registerRequestParam.getPassword());
		userInfoBean.setRealName(registerRequestParam.getRealName());
		userInfoBean.setEmail(registerRequestParam.getEmail());
		userInfoBean.setGender(registerRequestParam.getGender());
		
		registerService.doRegister(userInfoBean);
	}
}
