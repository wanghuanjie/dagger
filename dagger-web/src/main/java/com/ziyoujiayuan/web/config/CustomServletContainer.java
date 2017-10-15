package com.ziyoujiayuan.web.config;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer{

	@Override 
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		configurableEmbeddedServletContainer.setPort(8100);
	
		configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
		configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.EXPECTATION_FAILED,"/error.html"));
	    configurableEmbeddedServletContainer.setSessionTimeout(10,TimeUnit.MINUTES);
	 
	}
}
