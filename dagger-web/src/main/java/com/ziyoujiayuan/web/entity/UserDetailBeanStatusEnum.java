package com.ziyoujiayuan.web.entity;

/**
 * 用户表状态枚举
 * @Author wanghjbuf
 * @Date 2017年9月26日
 */
public enum UserDetailBeanStatusEnum {
	//正常
	normal(0),
	//冻结
	freeze(1),
	//注销
	logout(2),
	//待注册
	authstr(3);
	
	private Integer index; 
	 
	UserDetailBeanStatusEnum(Integer idx) { 
	  this.index = idx; 
	} 
	 
	public int getIndex() { 
	  return index; 
	} 
}
