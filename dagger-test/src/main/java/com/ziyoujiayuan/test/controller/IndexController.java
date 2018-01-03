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
	private static ThreadLocal<String> local = new ThreadLocal<>();
	private static InheritableThreadLocal<String> local1 = new InheritableThreadLocal<>();
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
	
	public static void main(String[] args) {
		System.out.println(local.get());
		System.out.println(local1.get());
		local.set("gebiloawang");
		System.out.println(local.get());
		System.out.println(local1.get());

	}
}
