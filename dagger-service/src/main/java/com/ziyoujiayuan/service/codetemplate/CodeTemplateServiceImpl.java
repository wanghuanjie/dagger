package com.ziyoujiayuan.service.codetemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.data.cons.exception.GeneralExceptionCons;

import lombok.extern.slf4j.Slf4j;

/**
 * Service代码模版
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
@Slf4j
@Service("com.ziyoujiayuan.service.codetemplate.CodeTemplateServiceImpl")
public class CodeTemplateServiceImpl {

	@Transactional
	public void doAddPrivilege() throws AppException{
		try {
			//TODO
			log.info("sssss");

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
