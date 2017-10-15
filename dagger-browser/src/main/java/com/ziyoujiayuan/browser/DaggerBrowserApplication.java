package com.ziyoujiayuan.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ziyoujiayuan.browser","com.ziyoujiayuan.web","com.ziyoujiayuan.data","com.ziyoujiayuan.core"})
@SpringBootApplication
public class DaggerBrowserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaggerBrowserApplication.class, args);
	}
}
