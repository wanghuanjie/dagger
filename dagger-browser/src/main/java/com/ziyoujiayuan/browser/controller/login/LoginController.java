package com.ziyoujiayuan.browser.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.browser.cons.ResultMsgCons;
import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;
import com.ziyoujiayuan.browser.serve.usermanage.LoginServe;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;
/**
 * 登录/登出功能相关
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginServe loginServe;
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String in() {
		return ViewsBasePathCons.VIEWS_BASEPATH+"login/index";
	}
	
	/**
	 * 登录功能
	 * @param model
	 * @return
	 */
	@RequestMapping("/doin")
	public ResponseJsonResult doin() {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			loginServe.login();
			
		    responseJsonResult.setMsg(ResultMsgCons.LOGIN_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 注销功能
	 * @param model
	 * @return
	 */
	@RequestMapping("/doout")
	public ResponseJsonResult doout() {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			loginServe.logout();
			
			responseJsonResult.setMsg(ResultMsgCons.LOGOUT_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
