package com.ziyoujiayuan.browser.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.browser.cons.ResultMsgCons;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 上传应用
 * @Author wanghjbuf
 * @Date 2017年10月19日
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

	   /**
	    * 上传demo(TODO 处理并发写入问题)
	    * 2017年3月11日
	    * @param request
	    * @param user
	    * @return
	    * @throws Exception
	    */
		@SuppressWarnings("rawtypes")
		@RequestMapping("/do")
		public ResponseJsonResult doUplaod(HttpServletRequest request) throws Exception{
			ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		    try {
		         boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		         if (isMultipart) {
		             FileItemFactory factory = new DiskFileItemFactory();
		             ServletFileUpload upload = new ServletFileUpload(factory);
		        
		        	     for (Iterator iter = upload.parseRequest(request).iterator(); iter.hasNext();) {
			        		  FileItem item = (FileItem)iter.next();
			        		  InputStream inputStream = null;
			        		  String filename="";
			        		
				          if (!(item.isFormField()))
				            	inputStream = item.getInputStream();
				          
				          if (inputStream == null)
						    	throw new AppException("未获取上传图片数据,操作失败！");
				            
				          filename = item.getName();
			              String realPath = new StringBuffer(getRealPath(request)).append(File.separator).append("filepool").append(File.separator).toString();
				          log.info("realPath="+realPath);
				
					      FileOutputStream fileOutputStream = new FileOutputStream(realPath + filename);
						  byte buffer[] = new byte[1024];
						  int len = 0;
						  while((len=inputStream.read(buffer))>0){
							fileOutputStream.write(buffer, 0, len);
						  }
						  inputStream.close();
					      fileOutputStream.close();
			            }
			      }
	
		         responseJsonResult.setMsg(ResultMsgCons.OPER_SUCCESS);
		         responseJsonResult.setSuccess(true);
		    }catch (Exception e) {
		      	 responseJsonResult.setMsg(e.getMessage());
		         responseJsonResult.setSuccess(false);
		        log.error(e.getMessage(), e);	
		    }
			return responseJsonResult;
		}
		
		/**
		 * 获取webapps路径
		 * 2017年3月11日
		 * @param request
		 * @return
		 * @throws Exception
		 */
		private String getRealPath(HttpServletRequest request) throws AppException{
			String webappsRoot = request.getSession().getServletContext().getRealPath("/");  
			
	        String[] rootArry = webappsRoot.split("/");  
	        StringBuilder webappsTemp = new StringBuilder();  
	        int i = 1;  
	        for(String nodePath : rootArry){  
	            ++i;  
	            if(i != rootArry.length){  
	            	webappsTemp.append(nodePath);  
	            	webappsTemp.append(File.separator);  
	            }  
	        }  
	        String webappsPath = webappsTemp.toString();
	        return webappsPath;
	    }
}
