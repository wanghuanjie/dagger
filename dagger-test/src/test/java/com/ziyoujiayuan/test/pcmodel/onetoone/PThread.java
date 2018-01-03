package com.ziyoujiayuan.test.pcmodel.onetoone;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class PThread extends Thread {

	private P p;
	
	/**
	 * 
	 */
	public PThread(P p) {
		// TODO Auto-generated constructor stub
		super();
		this.p = p;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				p.setValue();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
