package com.ziyoujiayuan.test.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.test.param.GeneralRemoteParam;
import com.ziyoujiayuan.test.serve.general.GeneralRemoteServe;
import com.ziyoujiayuan.web.base.BaseController;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 一般性远程调用压测
 * @Author wanghjbuf
 * @Date 2018年1月3日
 */
@Slf4j
@Controller
@RequestMapping("/general/remote")
public class GeneralRemoteController extends BaseController {

	@Autowired
	GeneralRemoteServe generalRemoteServe;
	
	@ResponseBody
	@RequestMapping("/dotest")
	public ResponseJsonResult doTest(GeneralRemoteParam generalRemoteParam){		
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			responseJsonResult.setData_collect(generalRemoteServe.doTest(generalRemoteParam));
			responseJsonResult.setMsg("操作成功");
			responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("系统错误，操作失败！",e);
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
		}
		return responseJsonResult;
	}
}
