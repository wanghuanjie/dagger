package com.ziyoujiayuan.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.web.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * web相关请求错误代码
 * @Author wanghjbuf
 * @Date 2017年10月31日
 */
@Slf4j
@Controller
@RequestMapping("/error")
public class WebErroController {

	/**
	 * 404请求问题
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/404")
	public ResponseJsonResult error404(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {			
		    responseJsonResult.setMsg(ResultMsgCons.WEB_ERROR_404);
            responseJsonResult.setSuccess(false);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 403请求问题
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/403")
	public ResponseJsonResult error403(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {			
		    responseJsonResult.setMsg(ResultMsgCons.WEB_ERROR_403);
            responseJsonResult.setSuccess(false);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * error统一处理
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/")
	public ResponseJsonResult error(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {			
		    responseJsonResult.setMsg(ResultMsgCons.WEB_ERROR);
            responseJsonResult.setSuccess(false);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
