package com.ziyoujiayuan.api.usermanage;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;

/**
 * 注册相关服务
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
public interface RegisterService {

	/**
	 * 执行注册
	 */
	public void doRegister(UserInfoBean userInfoBean) throws AppException;
}
