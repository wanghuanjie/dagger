package com.ziyoujiayuan.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @Author wanghjbuf
 * @Date 2018年3月1日
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ziyoujiayuan.crawler","com.ziyoujiayuan.web","com.ziyoujiayuan.data","com.ziyoujiayuan.core"})
@SpringBootApplication
public class DaggerCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaggerCrawlerApplication.class, args);
	}
}