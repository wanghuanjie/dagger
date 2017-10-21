package com.ziyoujiayuan.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 首页测试
 * @author wanghjbuf
 * @date 2017年10月16日
 */
@Slf4j
@Controller
public class IndexController {
	
	/**
	 * 主界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(){		
		log.info("欢迎来到daggerTest的世界");
		return "views/index";
	}
}
