package com.ziyoujiayuan.base.logback;

import com.ziyoujiayuan.base.logging.LoggingTracer;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 
 * @Author wanghjbuf
 * @Date 2018年2月16日
 */
public class LoggingTracerConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		String s = LoggingTracer.get();
		return s == null ? "no tracer" : s;
	}

}
