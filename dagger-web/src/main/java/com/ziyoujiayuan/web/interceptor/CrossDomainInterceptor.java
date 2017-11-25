package com.ziyoujiayuan.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Ajax跨域访问(借助Nginx的反向代理也可实现跨域相关)
 * @Author wanghjbuf
 * @Date 2017年11月3日
 */
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
		
//		StringBuffer originPath = new StringBuffer();
//		originPath = originPath.append(arg0.getScheme()).append("://").append(arg0.getServerName()).append(":").append(arg0.getServerPort());
//		log.info("path:>>>"+originPath.toString()+";local:"+arg0.getLocalPort()+";remote:"+arg0.getRemotePort()+";origin:"+arg0.getHeader("Origin"));
		
		arg1.setHeader("Access-Control-Allow-Origin", arg0.getHeader("Origin"));  
		arg1.setHeader("Access-Control-Allow-Headers", "Cookie,dagger_token,Content-Type,Content-Length, Authorization, Accept,X-Requested-With");  
		arg1.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");    
        arg1.setHeader("Access-Control-Allow-Credentials", "true");
		
        if ("OPTIONS".equals(arg0.getMethod())){
            return false;  
        }     
		return true;
	}

}
