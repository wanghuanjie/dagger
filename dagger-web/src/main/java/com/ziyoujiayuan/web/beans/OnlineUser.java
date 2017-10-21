package com.ziyoujiayuan.web.beans;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ziyoujiayuan.data.pojo.UserBasicInfo;
import com.ziyoujiayuan.web.cons.OnlineUserTypeEnum;

import lombok.Data;

/**
 * 当前用户
 * @Author wanghjbuf
 * @Date 2017年10月19日
 */
@Data
public class OnlineUser {
	public static final String DAGGER_ONLINEUESER = "dagger_online_user";
	
	public static OnlineUser current() {
		RequestAttributes att = RequestContextHolder.currentRequestAttributes();
		OnlineUser onlineUser = (OnlineUser) att.getAttribute(DAGGER_ONLINEUESER, RequestAttributes.SCOPE_SESSION);
		if (onlineUser == null) {
			onlineUser = new OnlineUser();
			att.setAttribute(DAGGER_ONLINEUESER, onlineUser, RequestAttributes.SCOPE_SESSION);
		}
		return onlineUser;
	}
	
	public static void clean() {
		RequestContextHolder.currentRequestAttributes().removeAttribute(DAGGER_ONLINEUESER, RequestAttributes.SCOPE_SESSION);
	}
	
	private boolean isOnline = true;//是否在线
	private String type = OnlineUserTypeEnum.GUEST.name();
	private UserBasicInfo userBasicInfo;//用户信息
	private String sessionId;//seesionId
    private String ipAddr;//ipAddr
    
}
