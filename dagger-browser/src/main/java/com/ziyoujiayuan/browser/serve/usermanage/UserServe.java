package com.ziyoujiayuan.browser.serve.usermanage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.usermanage.UserService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户管理相关
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Slf4j
@Service("com.ziyoujiayuan.browser.serve.usermanage.UserServe")
public class UserServe {
	
	@Autowired
	UserService userService;

	/**
	 * 查询全部用户
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager queryUsers(Map<String, Object> param) throws AppException{
		return userService.doQueryUsers(param);
	}
	
	/**
	 * 冻结/解冻操作
	 * @param userId
	 * @throws AppException
	 */
	public void toggleFreeze(long userId) throws AppException{
		log.info("执行冻结/解冻操作,被操作的账号ID为:"+userId);
		userService.doToggleFreeze(userId);
	}
}
