package com.ziyoujiayuan.browser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * index页面
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "views/index";
	}
}
