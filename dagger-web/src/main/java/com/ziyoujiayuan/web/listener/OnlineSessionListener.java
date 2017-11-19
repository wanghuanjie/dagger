package com.ziyoujiayuan.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * session初始化
 * @Author wanghjbuf
 * @Date 2017年11月19日
 */
@Component
public class OnlineSessionListener implements HttpSessionListener {

	@Autowired
	RedisTemplate<String ,Object> redisTemplate;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		synchronized (OnlineSessionListener.class) {
			Gson gson = new Gson();
			//记录累计用户,待优化
			long total_visit_user_count= 0L;
			String total_visit_user = redisTemplate.opsForValue().get("total_visit_user").toString();
			if(null != total_visit_user && !"".equals(redisTemplate.opsForValue().get("total_visit_user"))) {
				total_visit_user_count = Long.parseLong(total_visit_user);
			}
			
			redisTemplate.opsForValue().set("total_visit_user", gson.toJson(total_visit_user_count+1+""));
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
