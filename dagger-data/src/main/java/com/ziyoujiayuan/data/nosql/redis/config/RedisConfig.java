package com.ziyoujiayuan.data.nosql.redis.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Redis配置类
 * @Author wanghjbuf
 * @Date 2017年9月16日
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target,Method method,Object... params) {
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append(target.getClass().getName());
				sBuilder.append(method.getName());
				for(Object object : params) {
					sBuilder.append(object.toString());
				}
				return sBuilder.toString();
			}
		};
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
		redisCacheManager.setDefaultExpiration(60);
		
		return redisCacheManager;
	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
