package com.ziyoujiayuan.base.utils;

import java.util.UUID;

/**
 * UUID生成器
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
public class UuidUtils {

	/**
	 * 获取32位的UUID
	 * @return
	 */
    public static String getUUID(){ 
	    String uuid = UUID.randomUUID().toString(); 
	    return uuid.replaceAll("-", "");//去掉“-”符号
    }
}
