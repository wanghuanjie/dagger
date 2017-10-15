package com.ziyoujiayuan.data.nosql.redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Session共享配置
 * @Author wanghjbuf
 * @Date 2017年9月16日
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=30 * 60)
public class SessionConfig {

}
