package com.ziyoujiayuan.api.usermanage;

import java.util.Map;

import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;

/**
 * 用户管理接口
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
public interface UserService {

	/**
	 * 查询全部用户
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager doQueryUsers(Map<String, Object> param) throws AppException;
	
	/**
	 * 冻结/解冻用户
	 * @param userId
	 * @throws AppException
	 */
	public void doToggleFreeze(long userId) throws AppException; 
	
	/**
	 * 更新用户
	 * @param userInfoBean
	 * @throws AppException
	 */
    public void doUpdate(UserInfoBean userInfoBean) throws AppException;
    
    /**
     * 修改密码
     * @param userId
     * @param password
     * @throws AppException
     */
    public void doChangePwd(long userId, String password) throws AppException;
	
}
