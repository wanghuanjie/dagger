package com.ziyoujiayuan.service.usermanage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ziyoujiayuan.api.usermanage.UserService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.UserInfoBeanMapper;
import com.ziyoujiayuan.service.base.BaseService;

/**
 * 用户管理服务
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Service("com.ziyoujiayuan.service.usermanage.UserServiceImpl")
public class UserServiceImpl extends BaseService implements UserService{
	
	@Autowired
	UserInfoBeanMapper userInfoBeanMapper;

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.UserService#doQueryUsers(java.util.Map)
	 */
	@Transactional
	@Override
	public Pager doQueryUsers(Map<String, Object> param) throws AppException {
		// TODO Auto-generated method stub
        try {
			return queryForPager("com.ziyoujiayuan.data.sql.mybaties.mapper.def.usermanage.UserServiceMapper.selectUsers", param);
		} catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.UserService#doToggleFreeze(long)
	 */
	@Transactional
	@Override
	public void doToggleFreeze(long userId) throws AppException {
		// TODO Auto-generated method stub
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
    	        throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.UserService#doUpdate(com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean)
	 */
    public void doUpdate(UserInfoBean userInfoBean) throws AppException {
		try {
			if (0 >= userInfoBean.getUserId()) {
				throw new AppException("用户ID不能为空,操作失败！");
			}
			
			userInfoBeanMapper.updateByPrimaryKeySelective(userInfoBean);
		} catch (Exception e) {
			// TODO: handle exception
    	        throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
    }
    
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.UserService#changePwd(long, java.util.String)
	 */
    public void doChangePwd(long userId, String password) throws AppException {
		try {
			if (0 >= userId) {
				throw new AppException("用户ID不能为空,操作失败！");
			}
			
			UserInfoBean userInfoBean = new UserInfoBean();
			userInfoBean.setUserId(userId);
			userInfoBean.setPassword(password);
			userInfoBeanMapper.updateByPrimaryKeySelective(userInfoBean);
		} catch (Exception e) {
			// TODO: handle exception
    	        throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}    	
    }
    
}
