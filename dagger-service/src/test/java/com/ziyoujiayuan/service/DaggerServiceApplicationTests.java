package com.ziyoujiayuan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DaggerServiceApplication.class)
public class DaggerServiceApplicationTests {

	@Test
	public void demoMethod() {
		System.out.println(">>>>>>>testDemo");
	}
}