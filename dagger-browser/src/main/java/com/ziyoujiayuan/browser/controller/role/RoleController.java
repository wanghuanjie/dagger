package com.ziyoujiayuan.browser.controller.role;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.beans.role.RoleRequestParam;
import com.ziyoujiayuan.browser.cons.ResultMsgCons;
import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;
import com.ziyoujiayuan.browser.serve.usermanage.RoleServe;
import com.ziyoujiayuan.web.base.BaseController;
import com.ziyoujiayuan.web.param.ResponseJsonResult;
import com.ziyoujiayuan.web.utils.ParamUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色管理相关
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
@Slf4j
@RestController
@RequestMapping("/rolemanage")
public class RoleController extends BaseController{
	
	@Autowired
	RoleServe roleServe;
	
	/**
	 * 角色管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return ViewsBasePathCons.VIEWS_BASEPATH+"rolemanage/index";
	}
	
	/**
	 * 角色添加功能
	 * @param model
	 * @return
	 */
	@RequestMapping("/doadd")
	public ResponseJsonResult doadd(RoleRequestParam roleRequestParam) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			roleServe.addRole(roleRequestParam);
			
		    responseJsonResult.setMsg(ResultMsgCons.ADD_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 编辑角色
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/doeditrole")
	public ResponseJsonResult doeditrole(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            roleServe.editRole();	
            
		    responseJsonResult.setMsg(ResultMsgCons.EDIT_SUCCESS);
            responseJsonResult.setSuccess(true);
		} catch (Exception e) {
			responseJsonResult.setMsg(e.getMessage());
			responseJsonResult.setSuccess(false);
			log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
	
	/**
	 * 查询全部角色
	 * @param model
	 * @return
	 */
	@RequestMapping("/doqueryroles")
	public ResponseJsonResult doqueryroles(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			Map<String, Object> param = getParamMap(httpServletRequest);
			
			responseJsonResult.toJsonMap(roleServe.queryRoles(param));
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
	 * 查询角色对应的用户
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/doqueryusersbyrole")
	public ResponseJsonResult doqueryusersbyrole(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            long roleId = ParamUtils.getParameterLong(httpServletRequest, "roleId", -1L);
             if (-1 == roleId) {
				throw new AppException("角色ID不存在，操作失败！");
			}
			
			responseJsonResult.toJsonMap(roleServe.queryUsersByRole(roleId));
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
	 * 查询用户对应的角色
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/doqueryrolebyuser")
	public ResponseJsonResult doqueryrolebyuser(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
             long userId = ParamUtils.getParameterLong(httpServletRequest, "userId", -1L);
             if (-1 == userId) {
				throw new AppException("用户ID不存在，操作失败！");
			}
			
			responseJsonResult.setData_collect(roleServe.queryRoleByUser(userId));
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
	 * 用户解绑／关联角色
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/dobind")
	public ResponseJsonResult dobind(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            long userId = ParamUtils.getParameterLong(httpServletRequest, "userId", -1L);
            if (-1 == userId) {
				throw new AppException("用户ID不存在，操作失败！");
			}
            
            long roleId = ParamUtils.getParameterLong(httpServletRequest, "roleId", -1L);
            if (-1 == userId) {
				throw new AppException("角色ID不存在，操作失败！");
			}

            roleServe.togglebind(roleId, userId);
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
