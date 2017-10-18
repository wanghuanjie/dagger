package com.ziyoujiayuan.service.usermanage;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ziyoujiayuan.api.usermanage.UserService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.service.base.BaseService;

/**
 * 用户管理服务
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Service("com.ziyoujiayuan.service.usermanage.UserServiceImpl")
public class UserServiceImpl extends BaseService implements UserService{

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.UserService#doQueryUsers(java.util.Map)
	 */
	@Transactional
	@Override
	public Pager doQueryUsers(Map<String, Object> param) throws AppException {
		// TODO Auto-generated method stub
        try {
			return queryForPager("", param);
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

	}

}
