package com.ziyoujiayuan.api.usermanage;

import java.util.Map;

import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RoleInfoBean;

/**
 * 角色管理相关处理
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
public interface RoleService {

	/**
	 * 添加角色
	 * @param roleInfoBean
	 * @throws AppException
	 */
	public void doAddRole(RoleInfoBean roleInfoBean) throws AppException;
	
	/**
	 * 修改角色(TODO)
	 * @param roleInfoBean
	 * @throws AppException
	 */
	public void doEditRole(RoleInfoBean roleInfoBean) throws AppException;
	
	/**
	 * 查询所有角色
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager doQueryRoles(Map<String, Object> param) throws AppException;
	
	/**
	 * 查询用户通过角色
	 * @param roleId
	 * @return
	 * @throws AppException
	 */
	public Pager doQueryUsersByRole(long roleId) throws AppException;
	
	/**
	 * 查询用户通过角色
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	public RoleInfoBean doQueryRoleByUser(long userId) throws AppException;
	
	/**
	 * 角色绑定／解绑用户
	 * @param userId
	 * @param roleId
	 * @throws AppException
	 */
	public void doToggleBind(long userId ,long roleId) throws AppException;
}
