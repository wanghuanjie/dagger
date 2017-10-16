package com.ziyoujiayuan.api.usermanage;

/**
 * Login相关服务
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
public interface LoginService {

    /**
     * 登录操作
     */
	public void doLogin();
	
	/**
	 * 登出操作(注销操作)
	 */
	public void dologout();
}
