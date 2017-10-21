package com.ziyoujiayuan.browser.controller.codetemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller代码模版
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Slf4j
@Controller
@RequestMapping("/template")
public class CodeTemplateController {
	
	/**
	 * 页面请求模版
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return "views/index";
	}
	
	/**
	 * 功能请求模版
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/do")
	public ResponseJsonResult dosome() {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			//TODO 
			
		    responseJsonResult.setMsg("请求成功");
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
