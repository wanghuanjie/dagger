package com.ziyoujiayuan.browser.controller.mybaties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.data.sql.mybaties.mapper.PersonMapper;

/**
 * Mybaties测试类
 * @Author wanghjbuf
 * @Date 2017年9月14日
 */
@Controller
@RestController
@RequestMapping("/mybaties")
public class MybatiesController {

	@Autowired
	PersonMapper personMapper;
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> mapJsonDemo() {
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("person",personMapper.getPerson());
	    
	    System.out.println(personMapper.getPerson());
	    return map;
	}
}
