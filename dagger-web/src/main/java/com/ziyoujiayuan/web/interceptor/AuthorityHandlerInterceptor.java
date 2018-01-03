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
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.web.annotation.Privilege;
import com.ziyoujiayuan.web.beans.OnlineUser;
import com.ziyoujiayuan.web.cons.OnlineUserTypeEnum;

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
		
		log.info("prehandler:current_user_basic_info:{}"+OnlineUser.current().getUserBasicInfo());
		
		if (null == OnlineUser.current().getUserBasicInfo()) {
             return isEmptyUserBasicInfo(arg0, arg1, privilege);
		} else {
			loginService.updateDaggerTokenTimeOut(OnlineUser.current().getUserBasicInfo().getSessionId());
			return isNotEmptyUserBasicInfo(arg0, arg1, privilege);
		}
	}
	
	/**
	 * UserBasicInfo为空
	 * @param arg0
	 * @param arg1
	 * @param privilege
	 * @return
	 * @throws Exception
	 */
	private boolean isEmptyUserBasicInfo(HttpServletRequest arg0, HttpServletResponse arg1, Privilege privilege) throws AppException {		
		String stageRecord = "";
		try {
			if (currentUserBasicInfoFromCookies(arg0)) {//exist user_basic_info
				if (privilege != null) {//don't require permission control		
					UserBasicInfo currentUser = OnlineUser.current().getUserBasicInfo();
					log.info("The currentUser of the current application:{};",currentUser);

					if (currentUser == null) {
						stageRecord = "[user_basic_info is null;privilege isn't null]";
						arg1.sendRedirect("/login/fail");
						return false;
					} else if(currentUser != null && ! currentUser.containPrivilege(privilege.value())) {
						stageRecord = "[user_basic_info isn't null;privilege isn't null but not contain]";
						arg1.sendRedirect("/error/403");
						return false;
					} else {
						stageRecord = "[user_basic_info isn't null;privilege isn't null and contain]";
						return true;
					}
				} else {
					stageRecord = "[privilege is null]";
					return true;
				}			
			} else if(!currentUserBasicInfoFromCookies(arg0) && privilege != null){//is not exist user_basic_info and require permission control
				stageRecord = "[user_basic_info is null;privilege isn't null]";
				arg1.sendRedirect("/login/fail");
				return false;
			} else {
				stageRecord = "[privilege is null,else]";
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			stageRecord = e.getMessage();
			throw new AppException("AuthorityHandlerInterceptor-Exception",e);
		}finally {
			// TODO: handle finally clause
			log.info("The OnlinuUser of the current application is empty;The stage of arrival is {};",stageRecord);
		}		
	}
	
	/**
	 * UserBasicInfo不为空
	 * @param arg0
	 * @param arg1
	 * @param privilege
	 * @return
	 * @throws AppException
	 */
	private boolean isNotEmptyUserBasicInfo(HttpServletRequest arg0, HttpServletResponse arg1, Privilege privilege) throws AppException {		
		String stageRecord = "";
		try {
			if (privilege != null) {		
				UserBasicInfo currentUser = OnlineUser.current().getUserBasicInfo();
				log.info("The currentUser of the current application:{};",currentUser);

				if (currentUser == null) {
					stageRecord = "[user_basic_info is null;]";
					arg1.sendRedirect("/login/fail");
					return false;
				} else if(currentUser != null && !currentUser.containPrivilege(privilege.value())) {
					stageRecord = "[user_basic_info isn't null;privilege isn't null and not contain]";
					arg1.sendRedirect("/error/403");
					return false;
				} else {
					stageRecord = "[user_basic_info isn't null;privilege isn't null and contain]";
					return true;
				}
			} else {
				stageRecord = "[privilege is null]";
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			stageRecord = e.getMessage();
			throw new AppException("AuthorityHandlerInterceptor-Exception",e);
		}finally {
			// TODO: handle finally clause
			log.info("The OnlinuUser of the current application is not empty;The stage of arrival is {};",stageRecord);
		}		
	}
	
	/**
	 * 从Cookies中获取登录对象
	 * @param httpServletRequest
	 * @return 是否存在
	 * @throws Exception
	 */
	private boolean currentUserBasicInfoFromCookies(HttpServletRequest httpServletRequest) throws Exception{
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies == null) {
			return false;
		}
		for (Cookie cookie : cookies) {
 			if ("dagger_token".equals(cookie.getName())) {
				String token = cookie.getValue();
				log.info("The dagger_token of the current application:"+token+";");

				UserBasicInfo userBasicInfo = loginService.getUserBasicInfo(token);
				if (userBasicInfo != null) {
					OnlineUser.current().setUserBasicInfo(userBasicInfo);
					OnlineUser.current().setSessionId(token);
					OnlineUser.current().setType(OnlineUserTypeEnum.USER.name());
					
					log.info("The user_basic_info of the current application:"+userBasicInfo+";");
					return true;
				} else {
					return false;// token已过期
				}
			}
		}
		return false;
	}
	
	/**
	 * 从RequestHeader中获取当前用户信息
	 * @param httpServletRequest
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean currentUserBasicInfoFromRequestHeader(HttpServletRequest httpServletRequest) throws Exception{
	    String dagger_token = httpServletRequest.getHeader("dagger_token");
		log.info("The dagger_token of the current application:"+dagger_token+";");
		
	    if (null != dagger_token && !"".equals(dagger_token)) {
		    UserBasicInfo userBasicInfo = loginService.getUserBasicInfo(dagger_token);
		    if (userBasicInfo != null) {
				OnlineUser.current().setUserBasicInfo(userBasicInfo);
				OnlineUser.current().setSessionId(dagger_token);
				OnlineUser.current().setType(OnlineUserTypeEnum.USER.name());
				
				log.info("The user_basic_info of the current application:"+userBasicInfo+";");
				return true;
			} 
		}
	    return false;
	}
}
