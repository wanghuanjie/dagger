package com.ziyoujiayuan.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookies工具
 * @Author wanghjbuf
 * @Date 2017年10月24日
 */
public class CookiesUtils {

	/**
	 * 新建并获取Cookie
	 * @param key
	 * @return
	 */
	public  static Cookie addCookie(String key) {
		Cookie cookie = new Cookie("dagger_token", key);
		cookie.setPath("/");
		
		return cookie;
	}
	
	/**
	 * 清空cookie
	 * @return
	 */
	public static Cookie cleanCookie() {
		Cookie cookie = new Cookie("dagger_token", "");
		cookie.setMaxAge(0);
		
		return cookie;
	}
	
	/**
	 * 从Cookies中获取TokenId
	 * @return
	 */
	public static String getTokenIdFromCookies(HttpServletRequest httpServletRequest) {
		Cookie[] cookies = httpServletRequest.getCookies();
		String tokenId = "";
        for(Cookie item : cookies) {
        	    if("dagger_token".equals(item.getName())) {
        	    	   tokenId = item.getValue();
        	    }
        }
        
        return tokenId;
	}
	
}
