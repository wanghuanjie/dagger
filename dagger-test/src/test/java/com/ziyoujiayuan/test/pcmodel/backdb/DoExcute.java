package com.ziyoujiayuan.test.pcmodel.backdb;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class DoExcute {

	public static void main(String[] args) {
		DBTolls dbTolls = new DBTolls();
		

		
		for (int i = 0; i < 20; i++) {
			System.out.println(i);
			AThread aThread = new AThread(dbTolls);
			BThread bThread = new BThread(dbTolls);
			aThread.start();
			bThread.start();
		}

	}
}
