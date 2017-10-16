package com.ziyoujiayuan.browser.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页测试
 * @author wanghjbuf
 * @date 2017年10月16日
 */
@Controller
public class IndexController {
	
	private final Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 主界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("title", "测试标题");
		map.put("content", "测试内容");
		map.put("etraInfo", "额外信息，只对管理员显示");
		model.addAttribute("msg", map);
		
		logger.info("欢迎来到dagger的世界");
		return "views/index";
	}
}
