package com.ziyoujiayuan.automaker;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年10月31日
 */
@Configuration
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource({"classpath:dubbo/*.xml"})
public class DaggerAutoMakerDubboConfig {

}