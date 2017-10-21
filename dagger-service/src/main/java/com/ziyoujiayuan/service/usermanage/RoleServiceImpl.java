package com.ziyoujiayuan.service.usermanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ziyoujiayuan.api.usermanage.RoleService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.base.utils.UuidUtils;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.data.enums.usermanage.RoleStatusEnum;
import com.ziyoujiayuan.data.enums.usermanage.UserStatusEnum;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RoleInfoBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RoleInfoBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserRoleBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserRoleBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.RoleInfoBeanMapper;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.UserInfoBeanMapper;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.UserRoleBeanMapper;
import com.ziyoujiayuan.service.base.BaseService;

/**
 * 角色管理相关实现
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
@Service("com.ziyoujiayuan.service.usermanage.RoleServiceImpl")
public class RoleServiceImpl extends BaseService implements RoleService {

	@Autowired
	UserInfoBeanMapper userInfoBeanMapper;
	@Autowired
	RoleInfoBeanMapper roleInfoBeanMapper;
	@Autowired
	UserRoleBeanMapper userRoleBeanMapper;
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.RoleService#doAddRole(com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RoleInfoBean)
	 */
	@Transactional
	@Override
	public void doAddRole(RoleInfoBean roleInfoBean) throws AppException {
		// TODO Auto-generated method stub
		try {
			RoleInfoBeanExample roleInfoBeanExample = new RoleInfoBeanExample();
			roleInfoBeanExample.createCriteria().andRoleNameEqualTo(roleInfoBean.getRoleName()).andStatusEqualTo(RoleStatusEnum.ENABLED.name());
			int size = roleInfoBeanMapper.selectByExample(roleInfoBeanExample).size();
			if (size >= 1) {
				throw new AppException("角色名称冲突,操作失败！");
			}
			
			roleInfoBean.setStatus(RoleStatusEnum.ENABLED.name());
			roleInfoBean.setRoleCo(UuidUtils.getUUID());
			
			roleInfoBeanMapper.insertSelective(roleInfoBean);
		} catch (AppException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.RoleService#doEditRole(com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RoleInfoBean)
	 */
	@Transactional
	@Override
	public void doEditRole(RoleInfoBean roleInfoBean) throws AppException {
		// TODO Auto-generated method stub
        roleInfoBean.setStatus(RoleStatusEnum.ENABLED.name());
		roleInfoBeanMapper.updateByPrimaryKeySelective(roleInfoBean);
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.RoleService#doQueryRoles()
	 */
	@Transactional
	@Override
	public Pager doQueryRoles(Map<String, Object> param) throws AppException {
		// TODO Auto-generated method stub
        try {
			return queryForPager("", param);
		} catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.RoleService#doQueryUsersByRole(long)
	 */
	@Transactional
	@Override
	public Pager doQueryUsersByRole(long roleId) throws AppException {
		// TODO Auto-generated method stub
        try {
        	    Map<String, Object> param = new HashMap<>();
        	    param.put("roleId", roleId);
        	    
			return queryForPager("", param);
		} catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.RoleService#doQueryRoleByUser(long)
	 */
	@Transactional
	@Override
	public RoleInfoBean doQueryRoleByUser(long userId) throws AppException {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.RoleService#doAddRoleToUser(long ,long)
	 */
	@Transactional
	@Override
	public void doToggleBind(long userId ,long roleId) throws AppException {
		try {
			List<String> statusList = new ArrayList<>();
			statusList.add(UserStatusEnum.NORMAL.name());	
			UserInfoBeanExample userInfoBeanExample = new UserInfoBeanExample();
			userInfoBeanExample.createCriteria().andUserIdEqualTo(userId).andStatusIn(statusList);
			List<UserInfoBean> userInfoBeanList = userInfoBeanMapper.selectByExample(userInfoBeanExample);
			if (userInfoBeanList.size() < 1) {
				throw new AppException("当前用户不存在或者状态不可用,操作失败！");
			}
			
			RoleInfoBeanExample roleInfoBeanExample = new RoleInfoBeanExample();
			roleInfoBeanExample.createCriteria().andRoleIdEqualTo(roleId).andStatusEqualTo(RoleStatusEnum.ENABLED.name());
			List<RoleInfoBean> roleInfoBeanList = roleInfoBeanMapper.selectByExample(roleInfoBeanExample);
			if(roleInfoBeanList.size() < 1) {
				throw new AppException("当前角色不存在或者状态不可用,操作失败！");
			}
			
			//TODO 最好更改实体类添加创建时间选项
			UserRoleBeanExample userRoleBeanExample = new UserRoleBeanExample();
			userRoleBeanExample.createCriteria().andRoleIdEqualTo(roleId).andUserIdEqualTo(userId);
			int size = userRoleBeanMapper.selectByExample(userRoleBeanExample).size();
			
			UserRoleBean userRoleBean = new UserRoleBean();
			userRoleBean.setRoleId(roleId);
			userRoleBean.setUserId(userId);
			if(size < 1) {
			    userRoleBeanMapper.insertSelective(userRoleBean);
			}else {
				userRoleBeanMapper.deleteByPrimaryKey(userRoleBean);
			}			
			
		} catch (AppException e) {
			// TODO: handle exception
		    throw e;	
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

}
