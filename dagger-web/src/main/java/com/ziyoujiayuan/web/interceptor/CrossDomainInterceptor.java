package com.ziyoujiayuan.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * Ajax跨域访问
 * @Author wanghjbuf
 * @Date 2017年11月3日
 */
@Slf4j
@Component
public class CrossDomainInterceptor implements HandlerInterceptor {

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
		//解决跨域问题,配合nginx反向代理可解用第一句
		StringBuffer originPath = new StringBuffer();
//		originPath = originPath.append(arg0.getScheme()).append("://").append(arg0.getServerName()).append(":").append(arg0.getServerPort());
		originPath = originPath.append(arg0.getScheme()).append("://").append(arg0.getServerName()).append(":").append("8080");
		log.info("path:>>>"+originPath.toString()+";local:"+arg0.getLocalPort()+";remote:"+arg0.getRemotePort());
		
		arg1.setHeader("Access-Control-Allow-Origin", originPath.toString());  
		arg1.setHeader("Access-Control-Allow-Headers", "Cookie,dagger_token,Content-Type,Content-Length, Authorization, Accept,X-Requested-With");  
		arg1.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");    
        arg1.setHeader("Access-Control-Allow-Credentials", "true");
		
        if ("OPTIONS".equals(arg0.getMethod())){
            return false;  
        }     
		return true;
	}

}
