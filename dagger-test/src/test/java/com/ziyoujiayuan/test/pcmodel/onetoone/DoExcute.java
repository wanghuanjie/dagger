package com.ziyoujiayuan.test.pcmodel.onetoone;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class DoExcute {
	
	public static void main(String[] args) {
		String lock = new String("");
		P p = new P(lock);
		C c = new C(lock);
		
		PThread pThread = new PThread(p);
		CThread cThread =  new CThread(c);
		
		pThread.start();
		cThread.start();
	}
	
}
