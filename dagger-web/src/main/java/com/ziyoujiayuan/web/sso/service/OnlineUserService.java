package com.ziyoujiayuan.web.sso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ziyoujiayuan.data.sql.mybaties.entity.auto.UserDetailBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.UserDetailBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.UserRolesBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.UserDetailBeanMapper;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.UserRolesBeanMapper;
import com.ziyoujiayuan.web.entity.UserDetailBeanStatusEnum;
import com.ziyoujiayuan.web.sso.bean.OnlineUser;

/**
 * onlineUser
 * @Author wanghjbuf
 * @Date 2017年9月25日
 */
public class OnlineUserService implements UserDetailsService{

	@Autowired
	UserDetailBeanMapper userDetailBeanMapper;
	@Autowired
	UserRolesBeanMapper userRolesBeanMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		OnlineUser onlineUser = new OnlineUser();
		try {
			UserDetailBeanExample userDetailBeanExample = new UserDetailBeanExample();
			List<String> statusList = new ArrayList<>();
			statusList.add(UserDetailBeanStatusEnum.normal.name());
					
			userDetailBeanExample.createCriteria().andUserNameEqualTo(username).andStatusIn(statusList);
			
			List<UserDetailBean> userDetailBeanList = userDetailBeanMapper.selectByExample(userDetailBeanExample);
			if(null == userDetailBeanList || 1 != userDetailBeanList.size()) {
				throw new UsernameNotFoundException("该用户不存在,请注册！");
			}
			
			System.out.println(">>>>>>>>:"+userDetailBeanList);
			
			onlineUser = new OnlineUser(userDetailBeanList.get(0));
			UserRolesBeanExample userRolesBeanExample = new UserRolesBeanExample();
			userDetailBeanExample.createCriteria().andUserIdEqualTo(onlineUser.getUserid());
			onlineUser.setRoles(userRolesBeanMapper.selectByExample(userRolesBeanExample));
			
			System.out.println(onlineUser.getUserid()+":<<<<<<<<<:"+onlineUser.getRoles());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return onlineUser;
	}
	
	
}
