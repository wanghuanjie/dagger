package com.ziyoujiayuan.browser.controller.privilege;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.beans.privilege.PrivilegeRequestParam;
import com.ziyoujiayuan.browser.cons.ResultMsgCons;
import com.ziyoujiayuan.browser.cons.ViewsBasePathCons;
import com.ziyoujiayuan.browser.serve.usermanage.PrivilegeServe;
import com.ziyoujiayuan.web.base.BaseController;
import com.ziyoujiayuan.web.param.ResponseJsonResult;
import com.ziyoujiayuan.web.utils.ParamUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 权限管理相关
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Slf4j
@RestController
@RequestMapping("/privilegemanage")
public class PrivilegeController extends BaseController{
	
	@Autowired
	PrivilegeServe privliegeServe;
	
	/**
	 * 权限管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return ViewsBasePathCons.VIEWS_BASEPATH+"privilegemanage/index";
	}
	
	/**
	 * 新建权限请求
	 * @param model
	 * @return
	 */
	@RequestMapping("/doaddprivilege")
	public ResponseJsonResult doaddprivilege(PrivilegeRequestParam privilegeRequestParam) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			privliegeServe.addPrivilege(privilegeRequestParam);
			
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
	 * 修改权限请求
	 * @param model
	 * @return
	 */
	@RequestMapping("/doeditprivilege")
	public ResponseJsonResult doeditprivilege() {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
			privliegeServe.editPrivilege();
			
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
	 * 查询全部的权限
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryprivileges")
	public ResponseJsonResult queryprivileges(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            Map<String, Object> param = getParamMap(httpServletRequest);
            
			responseJsonResult.toJsonMap(privliegeServe.queryPrivileges(param));
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
	 * 通过角色查询权限
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryprivilegebyrole")
	public ResponseJsonResult queryprivilegebyuser(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            long roleId = ParamUtils.getParameterLong(httpServletRequest, "roleId",-1L);
            if (-1 == roleId) {
				throw new AppException("角色ID不存在，操作失败！");
			}
             
			responseJsonResult.toJsonMap(privliegeServe.queryPrivilegesByRole(roleId));
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
	 * 通过权限查询角色
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryrolebyprivilege")
	public ResponseJsonResult queryuserbyprivilege(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            long privilegeId = ParamUtils.getParameterLong(httpServletRequest, "privilegeId",-1L);
            if (-1 == privilegeId) {
				throw new AppException("权限ID不存在，操作失败！");
			}  
            
			responseJsonResult.toJsonMap(privliegeServe.queryRolesByPrivilege(privilegeId));
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
	 * 角色关联／解绑权限
	 * @param model
	 * @return
	 */
	@RequestMapping("/dobind")
	public ResponseJsonResult dobind(HttpServletRequest httpServletRequest) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
            long roleId = ParamUtils.getParameterLong(httpServletRequest, "roleId",-1L);
            if (-1 == roleId) {
				throw new AppException("角色ID不存在，操作失败！");
			}
			
            long privilegeId = ParamUtils.getParameterLong(httpServletRequest, "privilegeId",-1L);
            if (-1 == privilegeId) {
				throw new AppException("权限ID不存在，操作失败！");
			}  
            
			privliegeServe.togglebind(roleId, privilegeId);
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