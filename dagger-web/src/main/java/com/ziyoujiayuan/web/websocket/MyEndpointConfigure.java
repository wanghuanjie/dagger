package com.ziyoujiayuan.web.websocket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.websocket.server.ServerEndpointConfig;
/**
 * 
 * @Author wanghjbuf
 * @Date 2018年3月2日
 */
public class MyEndpointConfigure extends ServerEndpointConfig.Configurator implements ApplicationContextAware{

    /**
     * Spring application context.
     */
    private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      	MyEndpointConfigure.context = applicationContext;
    }
}
