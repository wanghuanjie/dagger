package com.ziyoujiayuan.crawler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @Author wanghjbuf
 * @Date 2018年3月1日
 */
@Configuration
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource({"classpath:dubbo/*.xml"})
public class DaggerCrawlerDubboConfig {

}
