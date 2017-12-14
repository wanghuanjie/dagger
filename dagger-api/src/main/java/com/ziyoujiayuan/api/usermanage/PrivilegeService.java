package com.ziyoujiayuan.api.usermanage;

import java.util.Map;

import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBean;

/**
 * 权限管理相关服务接口
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
public interface PrivilegeService {

	/**
	 * 权限添加
	 * @param privilegeInfoBean
	 * @throws AppException
	 */
	public void doAddPrivilege(PrivilegeInfoBean privilegeInfoBean) throws AppException;
	
	/**
	 * 修改权限
	 * @param privilegeInfoBean
	 * @throws AppException
	 */
	public void doEditPrivilege(PrivilegeInfoBean privilegeInfoBean) throws AppException;
	
	/**
	 * 查询全部权限
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager doQueryPrivileges(Map<String, Object> param) throws AppException;
	
	/**
	 * 查询角色权限
	 * @param roleId
	 * @return
	 * @throws AppException
	 */
	public Pager doQueryPrivilegesByRole(long roleId) throws AppException;
	
	/**
	 * 查询权限对应的角色
	 * @param privilegeId
	 * @return
	 * @throws AppException
	 */
	public Pager doQueryRoleByPrivileges(long privilegeId) throws AppException;
	
	/**
	 * 绑定／解绑角色权限
	 * @param privilegIds
	 * @param roleId
	 * @throws AppException
	 */
	public void doToggleBind(long[] privilegeIds , long roleId) throws AppException;
}
