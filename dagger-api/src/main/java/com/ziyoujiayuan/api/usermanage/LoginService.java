package com.ziyoujiayuan.api.usermanage;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;

/**
 * Login相关服务
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
public interface LoginService {

    /**
     * 登录操作
     * @param userInfoBean
     * @return
     * @throws AppException
     */
	public String doLogin(String sessionId ,UserInfoBean userInfoBean) throws AppException;
	
	/**
	 * 登出操作(注销操作)
	 * @throws AppException
	 */
	public void dologout(String dggerToken) throws AppException;
	
	/**
	 * 获取基础用户信息
	 * @param daggerToken
	 * @return
	 * @throws AppException
	 */
	public UserBasicInfo getUserBasicInfo(String daggerToken) throws AppException;
}
