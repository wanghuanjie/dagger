package com.ziyoujiayuan.automaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 自动化生成文档框架主目录
 * @Author wanghjbuf
 * @Date 2017年10月21日
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ziyoujiayuan.automaker","com.ziyoujiayuan.web","com.ziyoujiayuan.data","com.ziyoujiayuan.core"})
@SpringBootApplication
public class DaggerAutoMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaggerAutoMakerApplication.class, args);
	}
}
