package com.ziyoujiayuan.browser.beans.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.ziyoujiayuan.browser.beans.register.RegisterRequestParam;

import lombok.Data;

/**
 * 用户请求相关
 * @Author wanghjbuf
 * @Date 2017年11月12日
 */
@Data
@SuppressWarnings("unused")
public class UserRequestParam {
	
	/**
	 * 用户ID
	 */
    @NotNull
    @NotEmpty
	private long userId;
	
   /**
    * 账号(用户名)
    */
   @NotNull
   @NotEmpty
   private String nickName;
   
   /**
    * 邮件
    */
   @NotNull
   @NotEmpty
   @Email
   private String email;

   /**
    * 性别
    */
   private String gender;
}
