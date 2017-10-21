package com.ziyoujiayuan.data.pojo;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

/**
 * 用户基础信息
 * @Author wanghjbuf
 * @Date 2017年10月19日
 */
@SuppressWarnings("serial")
@Data
public class UserBasicInfo implements Serializable{

	private long userId;
	private String userName;
	private long roleId;
	private String roleName;
	private Set<PrivilegeBasicInfo> privileges;
	
	/**
	 * 判断当前用户是否存在当前权限
	 * @param privileges
	 * @return
	 */
	public boolean containPrivilege(String privileges) {
		
		return true;
	}
	
}
