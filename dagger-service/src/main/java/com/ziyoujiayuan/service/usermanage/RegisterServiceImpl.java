package com.ziyoujiayuan.service.usermanage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.RegisterService;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.base.utils.UuidUtils;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.data.enums.usermanage.UserStatusEnum;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.UserInfoBeanMapper;


/**
 * 注册服务实现
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
@Service("com.ziyoujiayuan.service.usermanage.RegisterServiceImpl")
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	UserInfoBeanMapper userInfoBeanMapper;
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.RegisterService#doRegister()
	 */
	@Override
	public void doRegister(UserInfoBean userInfoBean) throws AppException{
		try {
			// TODO Auto-generated method stub
			UserInfoBeanExample userInfoBeanExample = new UserInfoBeanExample();
			userInfoBeanExample.createCriteria().andAccountEqualTo(userInfoBean.getAccount());
	        int size = userInfoBeanMapper.selectByExample(userInfoBeanExample).size();
	        if (size >= 1) {
				throw new AppException("用户已经存在,操作失误！");
			}
			
			userInfoBean.setUserCo(UuidUtils.getUUID());
			userInfoBean.setStatus(UserStatusEnum.NORMAL.name());
			userInfoBean.setCreatTime(new Date());
			userInfoBean.setCreator("system");
			userInfoBean.setOperTime(new Date());
			userInfoBean.setOpertor("system");
			userInfoBeanMapper.insertSelective(userInfoBean);
			
			//TODO 默认角色以及默认权限
			
		} catch (AppException e) {
			throw e;
	    } catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

}
