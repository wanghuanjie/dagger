package com.ziyoujiayuan.browser.controller.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RabbitMq测试
 * @Author wanghjbuf
 * @Date 2017年9月27日
 */
@Controller
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@RequestMapping("/do")
	@ResponseBody
	public Map<String, Object> mapJsonDemo() {
	    Map<String, Object> map = new HashMap<String, Object>();

	    rabbitTemplate.convertAndSend("test-queue","do-message");
	    
	    map.put("success", true);
	    map.put("msg", "6666");
	    return map;
	}
}
