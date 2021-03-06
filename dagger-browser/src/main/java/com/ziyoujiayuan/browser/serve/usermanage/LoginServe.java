package com.ziyoujiayuan.browser.serve.usermanage;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.LoginService;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.beans.login.LoginRequestParam;
import com.ziyoujiayuan.data.pojo.PrivilegeBasicInfo;
import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;
import com.ziyoujiayuan.web.beans.OnlineUser;
import com.ziyoujiayuan.web.utils.CookiesUtils;

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
	public UserBasicInfo login(LoginRequestParam loginRequestParam,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws AppException{
		String sessionId = httpServletRequest.getSession().getId();
		log.info("login>>sessionId:"+sessionId);
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setAccount(loginRequestParam.getAccount());
		userInfoBean.setPassword(loginRequestParam.getPassword());
				
		String dagger_token = loginService.doLogin(sessionId ,userInfoBean);
		httpServletResponse.addCookie(CookiesUtils.addCookie(dagger_token));

		return loginService.getUserBasicInfo(dagger_token);
	}
	
	/**
	 * 登出相关操作
	 * @throws AppException
	 */
	public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
		log.info("logout");
		
		loginService.dologout(OnlineUser.current().getSessionId());
		httpServletResponse.addCookie(CookiesUtils.cleanCookie());
		OnlineUser.clean();
	}
	
	/**
	 * 获取UserInfo
	 * @param daggerToken
	 * @return
	 * @throws AppException
	 */
	public UserBasicInfo getObjectForSession(String daggerToken) throws AppException{
		return loginService.getUserBasicInfo(daggerToken);
	}
	
	/**
	 * 获取当前用户权限
	 * @return
	 * @throws AppException
	 */
	public Set<PrivilegeBasicInfo> getCurrentPrivilege() throws AppException{
		UserBasicInfo currentUser = OnlineUser.current().getUserBasicInfo();
		return currentUser.getPrivileges();
	}
}
