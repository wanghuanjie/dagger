package com.ziyoujiayuan.browser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;

import lombok.extern.slf4j.Slf4j;

/**
 * 首页
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
		log.info("欢迎来到dagger的世界");
		return ViewsBasePathCons.VIEWS_BASEPATH+"index";
	}
}
