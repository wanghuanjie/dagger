package com.ziyoujiayuan.browser.beans.register;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 注册请求参数
 * @Author wanghjbuf
 * @Date 2017年10月16日
 */
@Data
@SuppressWarnings("unused")
public class RegisterRequestParam {

   /**
    * 用户名
    */
   private String name;

   /**
    * 密码
    */
   private String password;	
   /**
    * 邮件
    */
   private String email;

   /**
    * 性别
    */
   private String gender;
   
   /**
    * 真实名称
    */
   private String realName;

}
