package com.ziyoujiayuan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ziyoujiayuan.data.sql.mybaties.mapper.PersonMapper;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DaggerServiceApplication.class)
public class DaggerServiceApplicationTests {

	@Autowired
	PersonMapper personMapper;
	
	@Test
	public void contextLoads() {
		System.out.println(">>>>>>>>>");
		System.out.println(personMapper.getPerson().getName());
		System.out.println("<<<<<<<<<");
	}
}