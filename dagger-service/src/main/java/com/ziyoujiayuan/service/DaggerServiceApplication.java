package com.ziyoujiayuan.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ziyoujiayuan.service","com.ziyoujiayuan.data","com.ziyoujiayuan.core"})
@SpringBootApplication
public class DaggerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaggerServiceApplication.class, args);
	}
}