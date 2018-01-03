package com.ziyoujiayuan.test.pcmodel.onetoone;

/**
 * 生产者
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class P {

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
	public P(String lock) {
		// TODO Auto-generated constructor stub
		super();
		this.lock=lock;
	}
	
	public void setValue() throws Exception{
		synchronized(lock) {
			if (!"".equals(ValueCon.value)) {
				lock.wait();
			}
			System.out.println("[P]-lock:"+lock);
			ValueCon.value = System.currentTimeMillis()+"";
			lock.notify();
		}
	}
}
