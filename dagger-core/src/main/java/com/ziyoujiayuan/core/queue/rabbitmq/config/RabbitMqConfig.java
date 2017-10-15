package com.ziyoujiayuan.core.queue.rabbitmq.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Rabbit-Mq配置
 * @Author wanghjbuf
 * @Date 2017年9月27日
 */
@Configuration
public class RabbitMqConfig implements CommandLineRunner {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Bean
	public Queue rabbitQueue() {
		return new Queue("test-queue");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
        rabbitTemplate.convertAndSend("test-queue","Test");
	}

}
