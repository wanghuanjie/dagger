package com.ziyoujiayuan.test.pcmodel.onetoone;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class CThread extends Thread {

	private C c;
	
	/**
	 * 
	 */
	public CThread(C c) {
		// TODO Auto-generated constructor stub
		super();
		this.c=c;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				c.getValue();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
