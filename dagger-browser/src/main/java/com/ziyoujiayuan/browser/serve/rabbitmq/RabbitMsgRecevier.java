package com.ziyoujiayuan.browser.serve.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Rabbit-mq接收
 * @Author wanghjbuf
 * @Date 2017年9月27日
 */
@Slf4j
@Component
public class RabbitMsgRecevier {
   
	@RabbitListener(queues = "test-queue")
    public void receive(String message) {
       log.info("test-queue:receive information:{};",message);
	}
   
}
