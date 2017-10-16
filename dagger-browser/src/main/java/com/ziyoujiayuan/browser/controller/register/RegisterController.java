package com.ziyoujiayuan.browser.controller.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.browser.beans.register.RegisterRequestParam;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

/**
 * 注册功能
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@Controller
@RestController
@RequestMapping("/register")
public class RegisterController {

	private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	/**
	 * 注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
        logger.info("欢迎来到注册界面");	
		return "views/register/index";
	}
	
	/**
	 * 注册功能
	 * @param model
	 * @return
	 */
	@RequestMapping("/doregister")
	public ResponseJsonResult doregister(RegisterRequestParam registerRequestParam) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			//TODO 待加入注册相关的操作
			
		    responseJsonResult.setMsg("注册成功");
            responseJsonResult.setSuccess(true);
            logger.info("注册成功");
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			logger.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
