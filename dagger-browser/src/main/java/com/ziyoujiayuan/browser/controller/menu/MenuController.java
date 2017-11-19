package com.ziyoujiayuan.browser.controller.menu;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.browser.serve.usermanage.LoginServe;
import com.ziyoujiayuan.data.pojo.PrivilegeBasicInfo;
import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.web.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;
import com.ziyoujiayuan.web.utils.CookiesUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年11月16日
 */
@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	LoginServe loginServe;
	
	@ResponseBody
	@RequestMapping("/personal")
	public ResponseJsonResult personal(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			Set<PrivilegeBasicInfo> menus = new HashSet<>();
            String sessionId = CookiesUtils.getTokenIdFromCookies(httpServletRequest);
            UserBasicInfo userBasicInfo = loginServe.getObjectForSession(sessionId);
            if (null != userBasicInfo) {
                menus = userBasicInfo.getPrivileges();
			}
             
            responseJsonResult.setData_collect(menus);
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
