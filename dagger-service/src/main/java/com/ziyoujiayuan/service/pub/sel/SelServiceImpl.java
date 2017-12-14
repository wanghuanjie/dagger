package com.ziyoujiayuan.service.pub.sel;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ziyoujiayuan.api.pub.sel.SelService;
import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;
import com.ziyoujiayuan.service.base.BaseService;

/**
 * 公共下拉选
 * @Author wanghjbuf
 * @Date 2017年12月11日
 */
@Service("com.ziyoujiayuan.service.pub.sel.SelServiceImpl")
public class SelServiceImpl extends BaseService implements SelService {

	/* (non-Javadoc)
	 * @see com.ziyoujiayuan.api.pub.sel.SelService#doQuerySecLevelPrivileges(java.util.Map)
	 */
	@Override
	public Pager doQueryPubSelPrivileges(Map<String, Object> param) throws AppException {
		// TODO Auto-generated method stub
		try {
			return queryForPager("com.ziyoujiayuan.data.sql.mybaties.mapper.def.usermanage.PrivilegeServiceMapper.selectPubSelPrivileges", param);
		} catch (Exception e) {
		    // TODO: handle exception
		    	throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}

}
