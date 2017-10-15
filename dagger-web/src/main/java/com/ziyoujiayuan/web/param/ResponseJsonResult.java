package com.ziyoujiayuan.web.param;

/**
 * Controller 接口返回结果参数
 * @Author wanghjbuf
 * @Date 2017年9月25日
 */
public class ResponseJsonResult {

	private Object data;
	
	private String msg = "操作成功";
	
	private boolean success = true;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ResponseResult [data=" + data + ", msg=" + msg + ", success=" + success + "]";
	}
}
