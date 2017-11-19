package com.ziyoujiayuan.service.usermanage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.StatisticsService;
import com.ziyoujiayuan.service.base.BaseService;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年11月19日
 */
@Service("com.ziyoujiayuan.service.usermanage.StatisticsServiceImpl")
public class StatisticsServiceImpl extends BaseService implements StatisticsService{

	@Autowired
	RedisTemplate<String ,Object> redisTemplate;
	@Value("${dagger.token.prefix}")
	private String daggerTokenPrefix;
	
	@Override
	public Map<String, Object> statisticUser() {
		Map<String, Object> userInfoMap = new HashMap<>();
		final Long online_users_size[] = {0L};
		
		//TODO 暂时先这样,与登录记录同时上线
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {	            
	            	online_users_size[0] = connection.dbSize();
	            return null;
            }
        });
		
		userInfoMap.put("online_users_size", online_users_size[0]);
		userInfoMap.put("total_users_size",redisTemplate.opsForValue().get("total_visit_user"));
		return userInfoMap;
	}
}
