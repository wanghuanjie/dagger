package com.ziyoujiayuan.service.codetemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;

/**
 * Service代码模版
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Service("com.ziyoujiayuan.service.codetemplate.CodeTemplateServiceImpl")
public class CodeTemplateServiceImpl {

	@Transactional
	public void doAddPrivilege() throws AppException{
		try {
			//TODO
			throw new AppException(">>>>>>");
			
		} catch (AppException e) {
			// TODO: handle exception
			throw e;
	    } catch (Exception e) {
			// TODO: handle exception
	    	    throw new AppException(GeneralExceptionCons.BASE_ERRO_MSG,e);
		}
	}
}
