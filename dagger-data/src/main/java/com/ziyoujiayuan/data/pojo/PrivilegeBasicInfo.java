package com.ziyoujiayuan.data.pojo;

import java.io.Serializable;
import java.util.Set;

import com.ziyoujiayuan.data.enums.usermanage.PrivilegeTypeEnum;

import lombok.Data;

/**
 * 权限基础表
 * @Author wanghjbuf
 * @Date 2017年10月19日
 */
@SuppressWarnings("serial")
@Data
public class PrivilegeBasicInfo implements Serializable{

	private String privilegeName;
	private String privilegeUrl;
	private int type;
	private Set<PrivilegeBasicInfo> childs;
	
	public boolean hasChilds() {
		if (this.type == PrivilegeTypeEnum.LEVELONE.getIndex()) {
			return true;
		} else if (this.type == PrivilegeTypeEnum.LEVELTWO.getIndex()) {
			return true;
		} else if (this.type == PrivilegeTypeEnum.LEVELTHREE.getIndex()){
			return false;
		} else {
			return false;
		}
	}
}
