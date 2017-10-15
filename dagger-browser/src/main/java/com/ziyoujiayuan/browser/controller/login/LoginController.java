package com.ziyoujiayuan.browser.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.web.param.ResponseJsonResult;

/**
 * 登录/登出功能相关
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@Controller
@RestController
@RequestMapping("/login")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String in(Model model) {
        logger.info("欢迎来到登录界面");	
		return "views/login/index";
	}
	
	/**
	 * 登录功能
	 * @param model
	 * @return
	 */
	@RequestMapping("/doin")
	public ResponseJsonResult doin(Model model) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			//TODO 待加入登录相关的操作
			
		    responseJsonResult.setMsg("登录成功");
            responseJsonResult.setSuccess(true);
            logger.info("登录成功");
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			logger.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 注销功能
	 * @param model
	 * @return
	 */
	@RequestMapping("/doout")
	public ResponseJsonResult doout(Model model) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			//TODO 待加入登出相关的操作
			
			responseJsonResult.setMsg("注销成功");
            responseJsonResult.setSuccess(true);
            logger.info("注销成功");
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			logger.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
