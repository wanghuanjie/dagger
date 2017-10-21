package com.ziyoujiayuan.service.demo;

import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.demo.DemoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@Slf4j
@Service("com.ziyoujiayuan.service.demo.DemoServiceImpl")
public class DemoServiceImpl implements DemoService{
	
	@Override
	public void doTest() {
		log.info(">>>>>test<<<<<<<");
	}
}
