package com.ziyoujiayuan.test.pcmodel.backdb;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class AThread extends Thread{
	
	private DBTolls dbTolls;

	/**
	 * 
	 */
	public AThread(DBTolls dbTolls) {
		// TODO Auto-generated constructor stub
		super();
		this.dbTolls = dbTolls;
	}
	
	@Override
	public void run() {
		try {
			dbTolls.preA();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
