package com.ziyoujiayuan.browser.controller.statistics;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.browser.serve.usermanage.StatisticsServe;
import com.ziyoujiayuan.web.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 统计功能相关
 * @Author wanghjbuf
 * @Date 2017年11月16日
 */
@Slf4j
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	
	@Autowired
	StatisticsServe statisticsServe;
	
	@ResponseBody
	@RequestMapping("/user")
	public ResponseJsonResult personal(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			Map<String, Object> result = new HashMap<>();
			result.putAll(statisticsServe.aboutUser());  
			
            responseJsonResult.setData_collect(result);
		    responseJsonResult.setMsg(ResultMsgCons.QUERY_SUCCESS);
            responseJsonResult.setSuccess(false);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}

}
