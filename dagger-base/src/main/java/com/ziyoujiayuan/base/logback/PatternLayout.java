package com.ziyoujiayuan.base.logback;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Author wanghjbuf
 * @Date 2018年2月16日
 */
public class PatternLayout extends ch.qos.logback.classic.PatternLayout {

	public static final Map<String, String> defaultConverterMap = new HashMap<String, String>();

	static {
		defaultConverterMap.putAll(ch.qos.logback.classic.PatternLayout.defaultConverterMap);
		defaultConverterMap.put("tracer", LoggingTracerConverter.class.getName());
	}

	public Map<String, String> getDefaultConverterMap() {
		return defaultConverterMap;
	}
}
