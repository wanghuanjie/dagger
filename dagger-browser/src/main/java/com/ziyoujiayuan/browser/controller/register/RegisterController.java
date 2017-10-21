package com.ziyoujiayuan.browser.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.browser.beans.register.RegisterRequestParam;
import com.ziyoujiayuan.browser.cons.ResultMsgCons;
import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;
import com.ziyoujiayuan.browser.serve.usermanage.RegisterServe;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 注册功能
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	RegisterServe registerServe;
	
	/**
	 * 注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return ViewsBasePathCons.VIEWS_BASEPATH+"register/index";
	}
	
	/**
	 * 注册功能
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doregister")
	public ResponseJsonResult doregister(RegisterRequestParam registerRequestParam) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			registerServe.registerUserInfo(registerRequestParam);
			
		    responseJsonResult.setMsg(ResultMsgCons.REGIST_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
