package com.ziyoujiayuan.browser.controller.statistics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;
import com.ziyoujiayuan.web.base.BaseController;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author wanghjbuf
 * @Date 2018年3月4日
 */
@Slf4j
@Controller
@RequestMapping("/websocketpage")
public class WebSocketTestPageController extends BaseController {

	/**
	 * websocketTest页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/test")
	public String index(Model model) {
		log.info("websocket-test");
		return ViewsBasePathCons.VIEWS_BASEPATH+"websocket/websocketTest";
	}
}
