package com.ziyoujiayuan.test.pcmodel.statck;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Author wanghjbuf
 * @Date 2017年12月23日
 */
public class MyStack {

	private List<String> list = new ArrayList<>();
	
	synchronized public void push() throws Exception{
		if (list.size() == 1) {
			this.wait();
		}
		
		list.add(new String(System.currentTimeMillis()+""));
		this.notify();
	}
	
	
	synchronized public String pop() throws Exception{
		if (list.size()<1) {
			this.wait();
		}
		
	    String result = list.get(0);
		list.remove(0);
		this.notify();
		return result;
	} 
}
