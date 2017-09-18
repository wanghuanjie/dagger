package com.ziyoujiayuan.browser.controller.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.browser.beans.Person;

/**
 * JsonDemoController
 * @Author wanghjbuf
 * @Date 2017年9月11日
 */
@Controller
@RequestMapping("/jsondemo")
public class JsonDemoController {

	@RequestMapping("/map")
	@ResponseBody
	public Map<String, Object> mapJsonDemo() {
		Person person = new Person("wanghjbuf1",18);
		Person person2 = new Person("wanghjbuf2",18);
		Person person3 = new Person("wanghjbuf3",18);
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("person", person);
	    map.put("person2", person2);
	    map.put("person3", person3);
	    
	    return map;
	}
}
