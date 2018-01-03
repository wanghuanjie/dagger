package com.ziyoujiayuan.test.serve.general;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.test.param.GeneralRemoteParam;

import lombok.extern.slf4j.Slf4j;

/**
 * 一般性远程压测服务
 * @Author wanghjbuf
 * @Date 2018年1月3日
 */
@Slf4j
@Service
public class GeneralRemoteServe {
	
	//TODO 成功率与失败率统计
	public GeneralRemoteParam doTest(GeneralRemoteParam generalRemoteParam) throws AppException{
		GeneralRemoteParam result = generalRemoteParam;
		try {
			int poolNum = generalRemoteParam.getPoolNum();
			int excuteNum = generalRemoteParam.getExcuteNum();
			
			ExecutorService executorService = Executors.newFixedThreadPool(poolNum);
		    
		    long currentTime = System.currentTimeMillis();
		    for (int i = 0; i < excuteNum; i++) {
		        executorService.execute(new GeneralRemoteServe().new TestThread(generalRemoteParam.getUrl(), generalRemoteParam.getMethodType(), generalRemoteParam.getParam()));
		    }
		    
		    executorService.shutdown();
		    while (!executorService.isTerminated()) {
		        
		    }
		    
		    long timeUse = System.currentTimeMillis() - currentTime;
		    log.info("Time-use:{};",timeUse );
		    result.setTimeUse(timeUse);
		    
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException("系统错误，操作失败！",e);
		}
		return result;
	}
	
	public class TestThread implements Runnable {
	    private String url;
	    private String methodType;
	    private String param;
	    
		public TestThread(String url, String methodType, String param) {
			// TODO Auto-generated constructor stub
			this.url = url;
			this.methodType = methodType;
			this.param = methodType;
		}
	
	    @SuppressWarnings("unchecked")
		@Override
	    public void run() {
	       try {
	    	   
	    	       Map<String, Object> map = (Map<String, Object>)JSON.parseObject(this.param, Map.class);
	    	       switch (this.methodType) {
				case "POST":
					HttpRequest post = HttpRequest.post(this.url);
			        String postBody = post.form(map).execute().body();
			        log.info("url:{},body:{};",this.url,postBody);
			        
					break;
                case "GET":
                 	HttpRequest get = HttpRequest.get(this.url);
			        String getBody = get.form(map).execute().body();
			        log.info("url:{},body:{};",this.url,getBody);  
			        
                	    break;
				default:
					log.info("Nothing to do;");
					
					break;
				}
	    	       
	       } catch (Exception e) {
	    	      throw e;
	       }
	   }
	}
}
