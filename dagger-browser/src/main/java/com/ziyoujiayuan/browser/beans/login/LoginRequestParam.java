package com.ziyoujiayuan.browser.beans.login;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 登录请求参数
 * @Author wanghjbuf
 * @Date 2017年10月20日
 */
@Data
public class LoginRequestParam {

	@NotNull
	@NotEmpty
	private String account;//登录账号
	
	@NotNull
	@NotEmpty
	private String password;//登录密码
	
	@NotNull
	@NotEmpty
	private String checkcode;//验证码
	
	private int termtype;//终端类型
}
