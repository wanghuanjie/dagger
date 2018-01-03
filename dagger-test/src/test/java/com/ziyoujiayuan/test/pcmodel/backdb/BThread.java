package com.ziyoujiayuan.test.pcmodel.backdb;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class BThread extends Thread {

	private DBTolls dBTolls;
	
	/**
	 * 
	 */
	public BThread(DBTolls dbTolls) {
		// TODO Auto-generated constructor stub
		super();
		this.dBTolls = dbTolls;
	}
	
	
	@Override
	public void run() {
		try {
			dBTolls.preB();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
