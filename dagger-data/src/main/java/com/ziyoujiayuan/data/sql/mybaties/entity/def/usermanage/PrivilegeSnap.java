package com.ziyoujiayuan.data.sql.mybaties.entity.def.usermanage;

import lombok.Data;

/**
 * 权限映射快照
 * @Author wanghjbuf
 * @Date 2017年10月20日
 */
@Data
public class PrivilegeSnap {

   private Long privilegeId;

   private String privilegeDec;

   private String privilegeName;

   private Integer privilegeType;

   private String privilegeAlias;

   private Long parentId;
}
