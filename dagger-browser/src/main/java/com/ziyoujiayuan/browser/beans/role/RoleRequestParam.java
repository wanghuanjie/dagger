package com.ziyoujiayuan.browser.beans.role;

import com.ziyoujiayuan.browser.beans.register.RegisterRequestParam;

import lombok.Data;

/**
 * 角色请求参数
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
@Data
@SuppressWarnings("unused")
public class RoleRequestParam {

	/**
	 * 角色描述
	 */
    private String roleDec;
    
    /**
     * 角色名称
     */
    private String roleName;
}
