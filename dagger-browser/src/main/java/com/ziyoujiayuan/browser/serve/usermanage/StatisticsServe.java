package com.ziyoujiayuan.browser.serve.usermanage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.StatisticsService;

/**
 * 统计功能
 * @Author wanghjbuf
 * @Date 2017年11月19日
 */
@Service("com.ziyoujiayuan.browser.serve.usermanage.StatisticsServe")
public class StatisticsServe {

	@Qualifier("com.ziyoujiayuan.service.usermanage.StatisticsServiceImpl")
	@Autowired
	StatisticsService statisticsService;
	
	public Map<String, Object> aboutUser() {
		return statisticsService.statisticUser();
	}
}
