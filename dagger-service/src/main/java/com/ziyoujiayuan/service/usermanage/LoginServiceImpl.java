package com.ziyoujiayuan.service.usermanage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import com.ziyoujiayuan.api.usermanage.LoginService;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.base.utils.UuidUtils;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.data.enums.usermanage.PrivilegeStatusEnum;
import com.ziyoujiayuan.data.enums.usermanage.PrivilegeTypeEnum;
import com.ziyoujiayuan.data.enums.usermanage.UserStatusEnum;
import com.ziyoujiayuan.data.pojo.PrivilegeBasicInfo;
import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserRoleBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserRoleBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.entity.def.usermanage.PrivilegeSnap;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.UserInfoBeanMapper;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.UserRoleBeanMapper;
import com.ziyoujiayuan.service.base.BaseService;

/**
 * 登录相关功能实现
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
@Service("com.ziyoujiayuan.service.usermanage.LoginServiceImpl")
public class LoginServiceImpl extends BaseService implements LoginService {
	
	@Autowired
	UserInfoBeanMapper userInfoBeanMapper;
	@Autowired
	UserRoleBeanMapper userRoleBeanMapper;
	@Autowired
	RedisTemplate<String,Object> redisTemplate;
	@Value("${dagger.token.prefix}")
	private String daggerTokenPrefix;
	@Value("${dagger.session.maxsurvival}")
	private long sessionMaxsurvival;
	
	
	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.LoginService#doLogin(com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.UserInfoBean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public String doLogin(String sessionId ,UserInfoBean userInfoBean) throws AppException{
		try {
			// TODO Auto-generated method stub
			UserInfoBeanExample userInfoBeanExample = new UserInfoBeanExample();
			userInfoBeanExample.createCriteria().andAccountEqualTo(userInfoBean.getAccount()).andPasswordEqualTo(userInfoBean.getPassword()).andStatusEqualTo(UserStatusEnum.NORMAL.name());
	        
			List<UserInfoBean> userInfoBeans = userInfoBeanMapper.selectByExample(userInfoBeanExample);
			if (userInfoBeans.size() < 1) {
				throw new AppException("用户或者账号不正确,操作失败！");
			}
			
			UserInfoBean currentUserInfo = userInfoBeans.get(0);
			
			UserBasicInfo userBasicInfo = new UserBasicInfo();
			userBasicInfo.setUserId(currentUserInfo.getUserId());
			userBasicInfo.setUserName(currentUserInfo.getNickName());
			userBasicInfo.setRoleName(currentUserInfo.getRealName());
			userBasicInfo.setAccount(currentUserInfo.getAccount());
			userBasicInfo.setEmail(currentUserInfo.getEmail());
			
			UserRoleBeanExample userRoleBeanExample = new UserRoleBeanExample();
			userRoleBeanExample.createCriteria().andUserIdEqualTo(currentUserInfo.getUserId());
			List<UserRoleBean> userRoleBeans = userRoleBeanMapper.selectByExample(userRoleBeanExample);
			if (userRoleBeans.size() < 1) {
				throw new AppException("当前用户未分配角色，操作失败！");
			}
			
			UserRoleBean currentUserRoleBean = userRoleBeans.get(0);
			userBasicInfo.setRoleId(currentUserRoleBean.getRoleId());
			
			Set<PrivilegeBasicInfo> privileges = new HashSet<PrivilegeBasicInfo>();
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("roleId", currentUserRoleBean.getRoleId());
			paramMap.put("status", PrivilegeStatusEnum.ENABLED.name());
			List<PrivilegeSnap> privilegeSnapList = (List<PrivilegeSnap>)queryForList("com.ziyoujiayuan.data.sql.mybaties.mapper.def.usermanage.LoginServiceMapper.selectPrivilegeByUserId", paramMap);
			
			for(PrivilegeSnap privilegeSnap : privilegeSnapList) {
				long privilegeId = privilegeSnap.getPrivilegeId();
				int type2 = privilegeSnap.getPrivilegeType().intValue();
				if (PrivilegeTypeEnum.LEVELTWO.getIndex() == type2) {
					PrivilegeBasicInfo privilegeBasicInfo = new PrivilegeBasicInfo();
					privilegeBasicInfo.setPrivilegeName(privilegeSnap.getPrivilegeName());
					privilegeBasicInfo.setType(type2);
					privilegeBasicInfo.setPrivilegeUrl(privilegeSnap.getPrivilegeUrl());
					Set<PrivilegeBasicInfo> privilegeBasicInfos = new HashSet<>();
					for(PrivilegeSnap privilegeSnapTemp : privilegeSnapList) {
						int type3 = privilegeSnapTemp.getPrivilegeType().intValue();
						if(PrivilegeTypeEnum.LEVELTHREE.getIndex() == type3 && privilegeId == privilegeSnapTemp.getParentId()) {
							PrivilegeBasicInfo privilegeBasicInfoChild = new PrivilegeBasicInfo();
							privilegeBasicInfoChild.setPrivilegeName(privilegeSnapTemp.getPrivilegeName());
							privilegeBasicInfoChild.setType(type3);
							privilegeBasicInfoChild.setPrivilegeUrl(privilegeSnapTemp.getPrivilegeUrl());
							
							privilegeBasicInfos.add(privilegeBasicInfoChild);
						}
					}
					privilegeBasicInfo.setChilds(privilegeBasicInfos);
					privileges.add(privilegeBasicInfo);
				} else {
					break;
				}
			}
			userBasicInfo.setPrivileges(privileges);	
			
			StringBuffer dagger_token = new StringBuffer(daggerTokenPrefix);
			dagger_token.append(UuidUtils.getUUID());
			userBasicInfo.setToken(dagger_token.toString());
			userBasicInfo.setSessionId(sessionId);
			
			if (sessionMaxsurvival == 0) sessionMaxsurvival = 180L;
	        Gson gson = new Gson();
	        redisTemplate.opsForValue().set(dagger_token.toString(), gson.toJson(userBasicInfo), sessionMaxsurvival,TimeUnit.SECONDS);

			return dagger_token.toString();
		} catch (AppException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.LoginService#dologout()
	 */
	@Override
	public void dologout(String dggerToken) throws AppException {
		// TODO Auto-generated method stub
		try {
			redisTemplate.delete(dggerToken);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.usermanage.LoginService#getUserBasicInfo(java.lang.String)
	 */
	@Override
	public UserBasicInfo getUserBasicInfo(String daggerToken) throws AppException {
		UserBasicInfo userBasicInfo = null;
		try {
			 Gson gson = new Gson();
			 if (redisTemplate.hasKey(daggerToken)) {
				 redisTemplate.expire(daggerToken, sessionMaxsurvival, TimeUnit.SECONDS);
                 userBasicInfo = gson.fromJson(redisTemplate.opsForValue().get(daggerToken).toString(), UserBasicInfo.class);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
		
		return userBasicInfo;
	}
}
