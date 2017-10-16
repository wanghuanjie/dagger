package com.ziyoujiayuan.data.enums.usermanage;

/**
 * 权限类型枚举
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
public enum PrivilegeTypeEnum {

	LEVELONE(1),
	LEVELTWO(2),
	LEVELTHREE(3);
	
	private Integer index; 
	   
	PrivilegeTypeEnum(Integer idx) { 
	   this.index = idx; 
	} 
	 
	public int getIndex() { 
	   return index; 
	} 
}
