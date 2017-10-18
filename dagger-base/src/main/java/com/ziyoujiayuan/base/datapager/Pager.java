package com.ziyoujiayuan.base.datapager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页控件基础类
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
@SuppressWarnings("serial")
public class Pager implements Serializable{
	
	private static int DEFAULT_PAGE_SIZE = 20;
	private int pageSize = DEFAULT_PAGE_SIZE; 

	private long start; 

	@SuppressWarnings("rawtypes")
	private List data; 

	private long totalCount; 

	@SuppressWarnings("rawtypes")
	public Pager() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	@SuppressWarnings("rawtypes")
	public Pager(long start, long totalSize, int pageSize, List data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}

	/**
	 * 总记录
	 * @return
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 总页码
	 * @return
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}
	
    /**
     * 页／记录数
     * @return
     */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 记录条数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getResult() {
		return data;
	}

	/**
	 * 当前页码
	 * @return
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 是否拥有下一页
	 * @return
	 */
	public boolean isHasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/**
	 * 是否拥有上一页
	 * @return
	 */
	public boolean isHasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 开始页码
	 * @param pageNo
	 * @return
	 */
	public static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	private static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
}
