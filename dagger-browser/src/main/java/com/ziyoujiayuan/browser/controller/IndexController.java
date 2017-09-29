package com.ziyoujiayuan.browser.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("title", "测试标题");
		map.put("content", "测试内容");
		map.put("etraInfo", "额外信息，只对管理员显示");
		
		model.addAttribute("msg", map);
		return "home";
	}
	
//	@RequestMapping("/login")
//	public String login(){
//		return "login";
//	}

//	/**
//	 * index页面
//	 * @return
//	 */
//	@RequestMapping("/")
//	public String index() {
//		return "views/index";
//	}
}
