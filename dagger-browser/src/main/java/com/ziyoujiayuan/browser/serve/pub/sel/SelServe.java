package com.ziyoujiayuan.browser.serve.pub.sel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.pub.sel.SelService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月11日
 */
@Service("com.ziyoujiayuan.browser.serve.pub.sel.SelServe")
public class SelServe {

	@Qualifier("com.ziyoujiayuan.service.pub.sel.SelServiceImpl")
	@Autowired
	SelService selService;
	
	/**
	 * 查询权限下拉选
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Pager querySecLevelPrivileges(Map<String, Object> param) throws AppException {
		return selService.doQueryPubSelPrivileges(param);
	}
}
