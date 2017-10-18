package com.ziyoujiayuan.browser.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.cons.ResultMsgCons;
import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;
import com.ziyoujiayuan.browser.serve.usermanage.UserServe;
import com.ziyoujiayuan.web.base.BaseController;
import com.ziyoujiayuan.web.param.ResponseJsonResult;
import com.ziyoujiayuan.web.utils.ParamUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户管理controller
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Slf4j
@RestController
@RequestMapping("/usermanage")
public class UerController extends BaseController{
	
	@Autowired
	UserServe userServe;
	
	/**
	 * 用户管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return ViewsBasePathCons.VIEWS_BASEPATH+"usermanage/index";
	}
	
	/**
	 * 用户管理查询
	 * @param model
	 * @return
	 */
	@RequestMapping("/doquery")
	public ResponseJsonResult doquery(HttpServletRequest request) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			Map<String, Object> param = getParamMap(request);
			
			responseJsonResult.toJsonMap(userServe.queryUsers(param));			
		    responseJsonResult.setMsg(ResultMsgCons.QUERY_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	
	/**
	 * 解冻/冻结用户
	 * @param model
	 * @return
	 */
	@RequestMapping("/togglefreeze")
	public ResponseJsonResult togglefreeze(HttpServletRequest request) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			long userId = ParamUtils.getParameterLong(request, "userId", -1L);
			if (-1 == userId) {
				throw new AppException("用户ID为空，操作失败！");
			}
			
            userServe.toggleFreeze(userId);			
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