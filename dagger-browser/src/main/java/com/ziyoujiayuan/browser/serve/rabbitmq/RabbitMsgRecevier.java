package com.ziyoujiayuan.browser.serve.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Rabbit-mq接收
 * @Author wanghjbuf
 * @Date 2017年9月27日
 */
@Component
public class RabbitMsgRecevier {
   
	@RabbitListener(queues = "test-queue")
    public void receive(String message) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:"+message);
	}
   
}
