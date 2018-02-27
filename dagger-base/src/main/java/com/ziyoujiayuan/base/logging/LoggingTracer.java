package com.ziyoujiayuan.base.logging;

import java.util.UUID;

/**
 * 
 * @Author wanghjbuf
 * @Date 2018年2月16日
 */
public class LoggingTracer {

	private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static void start() {
		threadLocal.set(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
	public static void set(String tracer) {
		threadLocal.set(tracer);
	}
	
	public static void remove() {
		threadLocal.remove();
	}
	
	public static String get() {
		return threadLocal.get();
	}
}
