package com.ziyoujiayuan.test.pcmodel.backdb;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class DBTolls {

	volatile private boolean preIsA = false;
	
	synchronized public void preA() throws Exception{
		while (preIsA == true) {
			wait();
		}
		
		for(int i=0;i<5;i++) {
			System.out.println("AAAAAA");
		}
		 
		preIsA = true;
		notifyAll();
		
	}
	
	synchronized public void preB() throws Exception{
		while (preIsA == false) {
			wait();
		}
		for(int i=0;i<5;i++) {
			System.out.println("BBBBBB");
		}
		
		preIsA = false;
		notifyAll();
	}
}
