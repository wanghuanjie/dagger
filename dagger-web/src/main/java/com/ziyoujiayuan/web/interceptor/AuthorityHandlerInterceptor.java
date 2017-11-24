package com.ziyoujiayuan.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ziyoujiayuan.api.usermanage.LoginService;
import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.web.annotation.Privilege;
import com.ziyoujiayuan.web.beans.OnlineUser;
import com.ziyoujiayuan.web.cons.OnlineUserTypeEnum;
import com.ziyoujiayuan.web.utils.ParamUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 权限拦截器
 * @Author wanghjbuf
 * @Date 2017年10月19日
 */
@Slf4j
@Component
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
		
		HandlerMethod handlerMethod = (HandlerMethod) arg2;
		Method method = handlerMethod.getMethod();
		Privilege privilege = method.getAnnotation(Privilege.class);
		
		if (null == OnlineUser.current().getUserBasicInfo()) {
             log.info("OnlinuUser is empty");
 			//先登录再判断
			if (currentOnlineUserFromRequestHeader(arg0)) {
				if (privilege != null) {		
					UserBasicInfo currentUser = OnlineUser.current().getUserBasicInfo();
					log.info("currentUser:"+currentUser);

					if (currentUser == null) {
						arg1.sendRedirect("/login/fail");
						return false;
					} else if(currentUser != null && ! currentUser.containPrivilege(privilege.value())) {
						arg1.sendRedirect("/error/403");
						return false;
					} else {
						return true;
					}
				} else {
					return true;
				}
				
			} else if(!currentOnlineUserFromRequestHeader(arg0) && privilege != null){
				arg1.sendRedirect("/login/fail");
				return false;
			} else {
				return true;
			}
		} else {
			log.info("OnlineUser is notEmupty!");
			//直接判断
			if (privilege != null) {		
				UserBasicInfo currentUser = OnlineUser.current().getUserBasicInfo();
				log.info("currentUser:"+currentUser);

				if (currentUser == null) {
					arg1.sendRedirect("/login/fail");
					return false;
				} else if(currentUser != null && ! currentUser.containPrivilege(privilege.value())) {
					arg1.sendRedirect("/error/403");
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
	}
	
	/**
	 * 从Cookies中获取登录对象
	 * @param httpServletRequest
	 * @return 是否存在
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean currentOnlineUserFromCookies(HttpServletRequest httpServletRequest) throws Exception{
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies == null) {
			return false;
		}
		for (Cookie cookie : cookies) {
 			if ("dagger_token".equals(cookie.getName())) {
				String token = cookie.getValue();
				
				UserBasicInfo userBasicInfo = loginService.getUserBasicInfo(token);
				if (userBasicInfo != null) {
					OnlineUser.current().setUserBasicInfo(userBasicInfo);
					OnlineUser.current().setSessionId(token);
					OnlineUser.current().setType(OnlineUserTypeEnum.USER.name());
					return true;
				} else {
					// token已过期
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * 从LocalStorage中获取当前用户
	 * @param httpServletRequest
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean currentOnlineUserFromLocalStorage(HttpServletRequest httpServletRequest) throws Exception{
	    String dagger_token = ParamUtils.getParameter(httpServletRequest, "dagger_token");
		log.info("dagger_token:"+dagger_token);
		
	    if (!"".equals(dagger_token)) {
		    UserBasicInfo userBasicInfo = loginService.getUserBasicInfo(dagger_token);
		    if (userBasicInfo != null) {
				OnlineUser.current().setUserBasicInfo(userBasicInfo);
				OnlineUser.current().setSessionId(dagger_token);
				OnlineUser.current().setType(OnlineUserTypeEnum.USER.name());
				
				log.info("userBasicInfo:"+userBasicInfo);
				return true;
			} 
		}
	    
	    return false;
	}
	
	/**
	 * 从Header中获取当前用户
	 * @param httpServletRequest
	 * @return
	 * @throws Exception
	 */
	private boolean currentOnlineUserFromRequestHeader(HttpServletRequest httpServletRequest) throws Exception{
		
	    String dagger_token = httpServletRequest.getHeader("dagger_token");
		log.info("dagger_token:"+dagger_token);
		
	    if (null != dagger_token && !"".equals(dagger_token)) {
		    UserBasicInfo userBasicInfo = loginService.getUserBasicInfo(dagger_token);
		    if (userBasicInfo != null) {
				OnlineUser.current().setUserBasicInfo(userBasicInfo);
				OnlineUser.current().setSessionId(dagger_token);
				OnlineUser.current().setType(OnlineUserTypeEnum.USER.name());
				
				log.info("userBasicInfo:"+userBasicInfo);
				return true;
			} 
		}
	    
	    return false;
	}

}
