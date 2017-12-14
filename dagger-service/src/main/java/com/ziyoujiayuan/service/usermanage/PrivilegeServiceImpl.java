package com.ziyoujiayuan.service.usermanage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.PrivilegeService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.base.utils.UuidUtils;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.data.enums.usermanage.PrivilegeStatusEnum;
import com.ziyoujiayuan.data.enums.usermanage.RoleStatusEnum;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RoleInfoBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RolePrivilegeBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.RolePrivilegeBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.PrivilegeInfoBeanMapper;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.RoleInfoBeanMapper;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.RolePrivilegeBeanMapper;
import com.ziyoujiayuan.service.base.BaseService;

/**
 * 权限管理实现
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
@Service("com.ziyoujiayuan.service.usermanage.PrivilegeServiceImpl")
public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {

	@Autowired
	RoleInfoBeanMapper roleInfoBeanMapper;
	@Autowired
	PrivilegeInfoBeanMapper privilegeInfoBeanMapper;
	@Autowired
	RolePrivilegeBeanMapper rolePrivilegeBeanMapper;
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.PrivilegeService#doAddPrivilege(com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBean)
	 */
	@Override
	public void doAddPrivilege(PrivilegeInfoBean privilegeInfoBean) throws AppException{
		try {
			PrivilegeInfoBeanExample privilegeInfoBeanExample = new PrivilegeInfoBeanExample();
			privilegeInfoBeanExample.createCriteria().andPrivilegeNameEqualTo(privilegeInfoBean.getPrivilegeName()).andStatusEqualTo(PrivilegeStatusEnum.ENABLED.name());
	        int size = privilegeInfoBeanMapper.selectByExample(privilegeInfoBeanExample).size();
			if (size >= 1) {
				throw new AppException("权限名称已经存在,操作失败！");
			}
	        
			// TODO Auto-generated method stub
			privilegeInfoBean.setPrivilegeCo(UuidUtils.getUUID());
			privilegeInfoBean.setStatus(PrivilegeStatusEnum.ENABLED.name());
			privilegeInfoBean.setCreatTime(new Date());
			privilegeInfoBean.setOperTime(new Date());
			
			privilegeInfoBeanMapper.insertSelective(privilegeInfoBean);
		} catch (AppException e) {
			// TODO: handle exception
			throw e;
	    } catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.PrivilegeService#doEditPrivilege(com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBean)
	 */
	@Override
	public void doEditPrivilege(PrivilegeInfoBean privilegeInfoBean) throws AppException{
		// TODO Auto-generated method stub
		try {
			privilegeInfoBean.setStatus(PrivilegeStatusEnum.ENABLED.name());
			privilegeInfoBeanMapper.updateByPrimaryKeySelective(privilegeInfoBean);
			
		} catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.PrivilegeService#doQueryPrivileges()
	 */
	@Override
	public Pager doQueryPrivileges(Map<String, Object> param) throws AppException{
		// TODO Auto-generated method stub
        try {
			return queryForPager("com.ziyoujiayuan.data.sql.mybaties.mapper.def.usermanage.PrivilegeServiceMapper.selectPrivileges", param);
		} catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.PrivilegeService#doQueryPrivilegesByRole(long)
	 */
	@Override
	public Pager doQueryPrivilegesByRole(long roleId) throws AppException{
		// TODO Auto-generated method stub
        try {
        	    Map<String, Object> param = new HashMap<>();
        	    param.put("role_id", roleId);
        	    param.put("privilege_type", 3);
        	    
			return queryForPager("com.ziyoujiayuan.data.sql.mybaties.mapper.def.usermanage.PrivilegeServiceMapper.selectRolePrivileges", param);
		} catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.PrivilegeService#doQueryRoleByPrivileges(long)
	 */
	@Override
	public Pager doQueryRoleByPrivileges(long privilegeId) throws AppException{
		// TODO Auto-generated method stub
        try {
        	    Map<String, Object> param = new HashMap<>();
        	    param.put("privilegeId", privilegeId);
        	    
			return queryForPager("", param);
		} catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.PrivilegeService#doToggleBind(long , long)
	 */
	@Override
	public void doToggleBind(long[] privilegeIds , long roleId) throws AppException {
		try {
		     RoleInfoBeanExample roleInfoBeanExample = new RoleInfoBeanExample();
		     roleInfoBeanExample.createCriteria().andRoleIdEqualTo(roleId).andStatusEqualTo(RoleStatusEnum.ENABLED.name());
		     int size = roleInfoBeanMapper.selectByExample(roleInfoBeanExample).size();
		     if(size < 1) {
		    	     throw new AppException("角色不存在或者无可用状态,操作失败！");
		     }
		     
		     RolePrivilegeBeanExample rolePrivilegeBeanExample = new RolePrivilegeBeanExample();
		     rolePrivilegeBeanExample.createCriteria().andRoleIdEqualTo(roleId);		     
		     rolePrivilegeBeanMapper.deleteByExample(rolePrivilegeBeanExample);
		     
		     for (int i = 0; i < privilegeIds.length; i++) {
			     RolePrivilegeBean rolePrivilegeBean = new RolePrivilegeBean();
			     rolePrivilegeBean.setPrivilegeId(privilegeIds[i]);
			     rolePrivilegeBean.setRoleId(roleId);
			     rolePrivilegeBean.setCreator("system");
			     rolePrivilegeBean.setCreatTime(new Date());
			     rolePrivilegeBean.setOpertor("system");
			     rolePrivilegeBean.setOperTime(new Date());
			     rolePrivilegeBeanMapper.insertSelective(rolePrivilegeBean);
			 }
		}catch (AppException e) {
			// TODO: handle exception	
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}
}
