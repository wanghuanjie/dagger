package com.ziyoujiayuan.web.base;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * BaseController类型
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
public abstract class BaseController {

	/**
	 * 转化request请求参数到Map
	 * @param request
	 * @return
	 */
	public Map<String,Object> getParamMap(HttpServletRequest request ){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        Enumeration<String> keys = request.getParameterNames();
        do{
            if(!keys.hasMoreElements())
                break;
            String key = (String)keys.nextElement();
            String value = request.getParameter(key);
            if(value != null && !value.equals("") && !value.equals("[]"))
                paramMap.put(key, value);
        } while(true);
        return paramMap;
    }
}
