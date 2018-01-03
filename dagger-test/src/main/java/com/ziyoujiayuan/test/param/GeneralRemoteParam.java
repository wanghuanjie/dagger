package com.ziyoujiayuan.test.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 一般性远程通知参数
 * @Author wanghjbuf
 * @Date 2018年1月3日
 */
@Data
public class GeneralRemoteParam {

	@NotNull
	@NotEmpty
	private int poolNum = 5;//默认线程池大小为5个
	
	@NotNull
	@NotEmpty
	private int excuteNum = 10;//默认执行次数为10次
	
	@NotNull
	@NotEmpty
	private String url;//url
	
	@NotNull
	@NotEmpty
	private String methodType = "POST";//类型
	
	@NotNull
	@NotEmpty
	private String param;
	
	private long timeUse;//耗时
}
