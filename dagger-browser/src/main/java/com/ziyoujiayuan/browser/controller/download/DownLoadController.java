package com.ziyoujiayuan.browser.controller.download;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.browser.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 下载
 * @Author wanghjbuf
 * @Date 2017年10月19日
 */
@Slf4j
@RestController
@RequestMapping("/download")
public class DownLoadController {

	@RequestMapping("/dotest")
	public ResponseJsonResult doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		try {
		     response.addHeader("Content-Disposition", "attachment;filename=" + new String("测试导出".getBytes("gb2312"), "ISO8859-1") + ".json");
		     response.setContentType("octets/stream");
		     OutputStream out = response.getOutputStream();
		     out.write("测试文件".getBytes());
		     out.close();
		      
		     responseJsonResult.setMsg(ResultMsgCons.OPER_SUCCESS);
		     responseJsonResult.setSuccess(true);
		} catch (Exception e) {
		     responseJsonResult.setMsg(e.getMessage());
		     responseJsonResult.setSuccess(false);
             log.error(e.getMessage(),e);
		}
		return responseJsonResult;
	}
}
