package com.ziyoujiayuan.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ziyoujiayuan.api.usermanage.LoginService;
import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.web.annotation.Privilege;
import com.ziyoujiayuan.web.beans.OnlineUser;
import com.ziyoujiayuan.web.cons.OnlineUserTypeEnum;

/**
 * 权限拦截器
 * @Author wanghjbuf
 * @Date 2017年10月19日
 */
public class AuthorityHandlerInterceptor implements HandlerInterceptor {
	
	@Qualifier("com.ziyoujiayuan.service.usermanage.LoginServiceImpl")
	@Autowired
	LoginService loginService;
	  

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		if (!(arg2 instanceof HandlerMethod)) {
			return true;
		}
		
		if (null == OnlineUser.current().getUserBasicInfo()) {
			if (currentOnlineUserFormCookies(arg0)) {
			   	return true;
			} 
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) arg2;
		Method method = handlerMethod.getMethod();
		Privilege privilege = method.getAnnotation(Privilege.class);
		
		if (privilege != null) {			
			UserBasicInfo currentUser = OnlineUser.current().getUserBasicInfo();
			if (currentUser == null) {
				arg1.sendRedirect("/login/index");
				return false;
			} else if(currentUser != null && ! currentUser.containPrivilege(privilege.value())) {
				arg1.sendRedirect("/403.html");
				return false;
			} 
		}
		
		return true;
	}
	
	/**
	 * 从Cookies中获取登录对象
	 * @param httpServletRequest
	 * @throws Exception
	 */
	public boolean currentOnlineUserFormCookies(HttpServletRequest httpServletRequest) throws Exception{
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies == null) {
			return true;
		}
		for (Cookie cookie : cookies) {
 			if ("dagger_token".equals(cookie.getName())) {
				String token = cookie.getValue();
				
				UserBasicInfo userBasicInfo = loginService.getUserBasicInfo(token);
				if (userBasicInfo != null) {
					OnlineUser.current().setUserBasicInfo(userBasicInfo);
					OnlineUser.current().setSessionId(token);
					OnlineUser.current().setType(OnlineUserTypeEnum.USER.name());
				} else {
					// token已过期
				}
				return false;
			}
		}
		return true;
	}

}
