package com.ziyoujiayuan.browser.controller.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.data.sql.mybaties.mapper.PersonMapper;

/**
 * Redis Cache	
 * @Author wanghjbuf
 * @Date 2017年9月16日
 */
@Controller
@RestController
@RequestMapping("/rediscache")
public class RedisCacheController {

	@Autowired
	PersonMapper personMapper;
	
	@RequestMapping("/list")
	@ResponseBody
	@Cacheable(value="user-key")
	public Map<String, Object> mapJsonDemo() {
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("person",personMapper.getPerson());
	    
	    System.out.println(personMapper.getPerson());
	    return map;
	}
	
	@RequestMapping("/list1")
	@ResponseBody
	public Map<String, Object> redisJsonDemo() {
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("person",personMapper.getPerson());
	    
	    System.out.println(personMapper.getPerson());
	    return map;
	}
}
