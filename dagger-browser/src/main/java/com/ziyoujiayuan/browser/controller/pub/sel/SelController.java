package com.ziyoujiayuan.browser.controller.pub.sel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.browser.serve.pub.sel.SelServe;
import com.ziyoujiayuan.data.enums.usermanage.PrivilegeStatusEnum;
import com.ziyoujiayuan.web.base.BaseController;
import com.ziyoujiayuan.web.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 公共下拉选
 * @Author wanghjbuf
 * @Date 2017年12月11日
 */
@Slf4j
@RestController
@RequestMapping("/pub/sel")
public class SelController extends BaseController{

	@Autowired
	SelServe selServe;
	
	/**
	 * 查询二级权限下拉选处理
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/seclevelprivileges")
	public ResponseJsonResult doQuerySecSelPrivileges(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            Map<String, Object> param = getParamMap(httpServletRequest);
            param.put("privilege_type", 2L);
            param.put("status", PrivilegeStatusEnum.ENABLED.name());
            
			responseJsonResult.toJsonMap(selServe.querySecLevelPrivileges(param));
		    responseJsonResult.setMsg(ResultMsgCons.QUERY_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
