package com.ziyoujiayuan.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试框架主目录
 * @Author wanghjbuf
 * @Date 2017年10月21日
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ziyoujiayuan.test","com.ziyoujiayuan.web","com.ziyoujiayuan.data","com.ziyoujiayuan.core"})
@SpringBootApplication
public class DaggerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaggerTestApplication.class, args);
	}
}
