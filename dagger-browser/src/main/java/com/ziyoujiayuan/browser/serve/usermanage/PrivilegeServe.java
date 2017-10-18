package com.ziyoujiayuan.browser.serve.usermanage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.PrivilegeService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.beans.privilege.PrivilegeRequestParam;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 权限服务相关
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
@Slf4j
@Service("com.ziyoujiayuan.browser.serve.usermanage.PrivilegeServe")
public class PrivilegeServe {
	
	@Qualifier("com.ziyoujiayuan.service.usermanage.PrivilegeServiceImpl")
	@Autowired
	PrivilegeService privilegeService;

	/**
	 * 权限新建
	 * @param privilegeRequestParam
	 * @throws AppException
	 */
	public void addPrivilege(PrivilegeRequestParam privilegeRequestParam) throws AppException{
		log.info("权限新建开始");
		PrivilegeInfoBean privilegeInfoBean = new PrivilegeInfoBean();
		privilegeInfoBean.setPrivilegeDec(privilegeRequestParam.getPrivilegeDec());
		privilegeInfoBean.setPrivilegeName(privilegeRequestParam.getPrivilegeName());
		privilegeInfoBean.setPrivilegeType(privilegeRequestParam.getPrivilegeType());
		privilegeInfoBean.setPrivilegeUrl(privilegeRequestParam.getPrivilegeUrl());
		
		privilegeService.doAddPrivilege(privilegeInfoBean);
	}
	
	/**
	 * 权限编辑
	 * @throws AppException
	 */
	public void editPrivilege() throws AppException {
		
	}
	
	/**
	 * 查询全部权限
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager queryPrivileges(Map<String, Object> param) throws AppException {
		return privilegeService.doQueryPrivileges(param);
	}
	
	/**
	 * 通过角色查询权限
	 * @param roleId
	 * @return
	 * @throws AppException
	 */
	public Pager queryPrivilegesByRole(long roleId) throws AppException {
		return privilegeService.doQueryPrivilegesByRole(roleId);
	}
	
	/**
	 * 通过权限查询角色
	 * @param privilegeId
	 * @return
	 * @throws AppException
	 */
	public Pager queryRolesByPrivilege(long privilegeId) throws AppException {
		return privilegeService.doQueryRoleByPrivileges(privilegeId);
	}
	
	/**
	 * 权限解除／关联角色
	 * @param roleId
	 * @param privilegeId
	 */
	public void togglebind(long roleId , long privilegeId) throws AppException {
		privilegeService.doToggleBind(privilegeId, roleId);
	}
}
