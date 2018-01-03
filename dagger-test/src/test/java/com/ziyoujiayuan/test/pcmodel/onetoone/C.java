package com.ziyoujiayuan.test.pcmodel.onetoone;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class C {

	private String lock;

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}
	
	/**
	 * 
	 */
	public C(String lock) {
		// TODO Auto-generated constructor stub
		super();
		this.lock = lock;
	}
	
	public void getValue() throws Exception{
		synchronized(lock) {
			if ("".equals(ValueCon.value)) {
				lock.wait();
			}
			System.out.println("[C]-lock:"+lock);
			ValueCon.value = "";
			lock.notify();
		}
	}
}
