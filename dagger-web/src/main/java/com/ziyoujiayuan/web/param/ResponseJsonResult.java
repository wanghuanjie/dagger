package com.ziyoujiayuan.web.param;

import com.ziyoujiayuan.base.datapager.Pager;

import lombok.Data;

/**
 * Controller 接口返回结果参数
 * @Author wanghjbuf
 * @Date 2017年9月25日
 */
@Data
public class ResponseJsonResult {
	//数据集合
	private Object data_collect;
	//数据计数
	private long data_count = 1L;
	//提示信息
	private String msg = "操作成功";
	//请求结果
	private boolean success = true;
	
	/**
	 * 分页调用数据
	 * @param pager
	 */
	public void toJsonMap(Pager pager) {
		if (null == pager) {
			this.data_collect = new Object();
			this.data_count = 1L;
		} else {
			this.data_collect = pager.getResult();
			this.data_count = pager.getTotalCount();
		}
	}
}
