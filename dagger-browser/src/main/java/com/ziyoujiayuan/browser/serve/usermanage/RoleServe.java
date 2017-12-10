package com.ziyoujiayuan.browser.serve.usermanage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.RoleService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.beans.role.RoleRequestParam;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RoleInfoBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色管理相关处理
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
@Slf4j
@Service("com.ziyoujiayuan.browser.serve.usermanage.RoleServe")
public class RoleServe {

	@Qualifier("com.ziyoujiayuan.service.usermanage.RoleServiceImpl")
	@Autowired
	RoleService roleService;
	
	/**
	 * 新建角色
	 * @param roleRequestParam
	 * @throws AppException
	 */
	public void addRole(RoleRequestParam roleRequestParam) throws AppException {
		RoleInfoBean roleInfoBean = new RoleInfoBean();
		log.info("新建角色开始");
		roleInfoBean.setRoleName(roleRequestParam.getRoleName());
		roleInfoBean.setRoleDec(roleRequestParam.getRoleDec());
		
		roleService.doAddRole(roleInfoBean);
	}
	
	/**
	 * 角色编辑
	 * @throws AppException
	 */
	public void editRole() throws AppException {
		
	}
	
	/**
	 * 查询所有角色
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager queryRoles(Map<String, Object> param) throws AppException {
		return roleService.doQueryRoles(param);
	}
	
	/**
	 * 通过role查询users
	 * @param roleId
	 * @return
	 * @throws AppException
	 */
	public Pager queryUsersByRole(long roleId) throws AppException {
		return roleService.doQueryUsersByRole(roleId);
	}
	
	/**
	 * 通过user查询role
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	public Map<String, Object> queryRoleByUser(long userId) throws AppException {
		return roleService.doQueryRoleByUser(userId);
	}
	
	/**
	 * 角色解除／关联用户
	 * @param roleId
	 * @param userId
	 * @throws AppException
	 */
	public void togglebind(long roleId ,long userId) throws AppException {
		roleService.doToggleBind(userId, roleId);
	}
}
