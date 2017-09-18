package com.ziyoujiayuan.browser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.ziyoujiayuan.data.sql.mybaties.mapper.PersonMapper;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"com.ziyoujiayuan.browser","com.ziyoujiayuan.web","com.ziyoujiayuan.data"})
@SpringBootTest
public class DaggerBrowserApplicationTests {

	@Autowired
	PersonMapper personMapper;
	
	@Test
	public void contextLoads() {
		System.out.println(">>>>>>>>>");
		System.out.println(personMapper.getPerson());
		System.out.println("<<<<<<<<<");
	}
}
