package com.ziyoujiayuan.browser.beans.privilege;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 权限添加请求参数
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Data
public class PrivilegeRequestParam {

   @NotNull
   @NotEmpty
   private String privilegeDec;//权限描述
   
   @NotNull
   @NotEmpty
   private String privilegeName;//权限名称
   
   @NotNull
   @NotEmpty
   private Integer privilegeType;//权限类型
   
   private String privilegeUrl;//权限URL(默认为空)
}
