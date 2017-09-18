package com.ziyoujiayuan.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.ziyoujiayuan.browser","com.ziyoujiayuan.web","com.ziyoujiayuan.data"})
@SpringBootApplication
public class DaggerBrowserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaggerBrowserApplication.class, args);
	}
}
