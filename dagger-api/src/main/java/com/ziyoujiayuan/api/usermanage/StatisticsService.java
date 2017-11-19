package com.ziyoujiayuan.api.usermanage;

import java.util.Map;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年11月19日
 */
public interface StatisticsService {

	/**
	 * 统计用户相关信息
	 * @param daggerToken
	 * @return
	 */
	public Map<String, Object> statisticUser();
}
