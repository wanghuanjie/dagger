package com.ziyoujiayuan.browser.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.beans.login.LoginRequestParam;
import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;
import com.ziyoujiayuan.browser.serve.usermanage.LoginServe;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.web.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;
/**
 * 登录/登出功能相关
 * @Author wanghjbuf
 * @Date 2017年10月13日
 */
@Slf4j
@Controller
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
	 * 登录功能失败
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fail")
	public ResponseJsonResult fail() {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {			
		    responseJsonResult.setMsg(ResultMsgCons.NO_LOGIN);
            responseJsonResult.setSuccess(false);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 登录功能
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doin")
	public ResponseJsonResult doin(LoginRequestParam loginRequestParam,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			
			responseJsonResult.setData_collect(loginServe.login(loginRequestParam,httpServletRequest,httpServletResponse));			
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
	@ResponseBody
	@RequestMapping("/doout")
	public ResponseJsonResult doout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			loginServe.logout(httpServletRequest,httpServletResponse);
			
			responseJsonResult.setMsg(ResultMsgCons.LOGOUT_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 获取用户信息
	 * @param httpServletRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/currentUserInfo")
	public ResponseJsonResult getSession(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
		    String sessionId = httpServletRequest.getHeader("dagger_token");
		    if ("".equals(sessionId)) {
				throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG);
			}

            responseJsonResult.setData_collect(loginServe.getObjectForSession(sessionId));
			responseJsonResult.setMsg(ResultMsgCons.OPER_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 当前用户的权限
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/currentPrivilege")
	public ResponseJsonResult getCurrentPrivilege() {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            responseJsonResult.setData_collect(loginServe.getCurrentPrivilege());
			responseJsonResult.setMsg(ResultMsgCons.OPER_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
