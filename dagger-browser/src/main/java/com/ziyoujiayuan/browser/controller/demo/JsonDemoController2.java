package com.ziyoujiayuan.browser.controller.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.browser.beans.Person;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年9月11日
 */
@RestController
@RequestMapping("/jsondemo")
public class JsonDemoController2 {

	@RequestMapping("/list")
	public List<Person> queryPersonList(){
		List<Person> resultList = new ArrayList<Person>();
		Person person = new Person("wanghjbuf",20);
		Person person2 = new Person("wanghjbuf1",21);
		
		resultList.add(person);
		resultList.add(person2);
		
		return resultList;
	}
}
