package com.ziyoujiayuan.service.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.demo.DemoService;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@Service("com.ziyoujiayuan.service.demo.DemoServiceImpl")
public class DemoServiceImpl implements DemoService{

	private final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	@Override
	public void doTest() {
		logger.info(">>>>>test<<<<<<<");
	}
}
