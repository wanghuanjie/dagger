package com.ziyoujiayuan.api.pub.sel;

import java.util.Map;

import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;

/**
 * 公共下拉选服务
 * @Author wanghjbuf
 * @Date 2017年12月11日
 */
public interface SelService {

	/**
	 * 查询下拉选权限
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager doQueryPubSelPrivileges(Map<String, Object> param) throws AppException;
}
