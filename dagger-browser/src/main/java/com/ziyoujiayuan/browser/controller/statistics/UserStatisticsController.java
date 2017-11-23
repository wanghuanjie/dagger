package com.ziyoujiayuan.browser.controller.statistics;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.browser.serve.usermanage.StatisticsServe;
import com.ziyoujiayuan.web.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年11月22日
 */
@Slf4j
@Controller
public class UserStatisticsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
	@Autowired
	StatisticsServe statisticsServe;
	
	@Scheduled(fixedRate = 1000)
	@ResponseBody
    @SendTo("/daggerbroker/test")
	public ResponseJsonResult test() {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			Map<String, Object> result = new HashMap<>();
            responseJsonResult.setData_collect(new Date());
			
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
	
	@ResponseBody
    @MessageMapping("/send")
    @SendTo("/daggerbroker/sendtest")
	public ResponseJsonResult sendtest(String value) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {			
            responseJsonResult.setData_collect(value);
		    responseJsonResult.setMsg(ResultMsgCons.QUERY_SUCCESS);
            responseJsonResult.setSuccess(false);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	@ResponseBody
    @MessageMapping("/reciver")
	public void reciver(String value) {
        messagingTemplate.convertAndSend("/send", value);
	}
	
	@ResponseBody
	@Scheduled(fixedRate = 1000)
    @SendTo("/daggerbroker/personal")
	public ResponseJsonResult personal() {
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
