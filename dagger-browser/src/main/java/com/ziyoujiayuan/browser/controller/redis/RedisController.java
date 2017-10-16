package com.ziyoujiayuan.browser.controller.redis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.browser.beans.Person;

/**
 * Redis 基础测试demo
 * @Author wanghjbuf
 * @Date 2017年9月16日
 */
@Controller
@RestController
@RequestMapping("/redis")
public class RedisController {

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/do")
	@ResponseBody
	public Map<String, Object> mapJsonDemo() {
	  Map<String, Object> map = new HashMap<String, Object>();
	  
	  stringRedisTemplate.opsForValue().set("key-1", "llllll");
	  System.out.println(">1<");
	  Person person = new Person("wanghj",new Integer(18));
	  redisTemplate.opsForValue().set(person.getName(), person);
	  System.out.println(">2<");
	  map.put("data", "list");
	  return map;
	}
}
