package com.ziyoujiayuan.web.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置
 * @Author wanghjbuf
 * @Date 2017年11月22日
 */
@Configuration
public class WebSocketConfig {

	@Bean 
	public MyEndpointConfigure customSpringConfigurator() {
	    return new MyEndpointConfigure(); // This is just to get context
	}
	
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
