package com.edu.bean;

import java.util.List;

/**
 * 封装服务器响应的数据
 * @author daixianren
 *
 */
public class PageBean {
	// 页面传递过来的
	private int pageNo ;// 当前页
	private int pageSize ;// 每页显示多少条数据
	// 从数据库中查询出来的数据
	private List<?> data ;// 页面显示的数据
	private int rowCount ;// 总记录数
	// 计算出来的数据
	private int pageCount ;// 总页码
	private int startIndex ; // 起始的索引
	private int endIndex ;// 结束的索引
	
	
	
	public PageBean(int pageNo, int pageSize, List<?> data, int rowCount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.data = data;
		this.rowCount = rowCount;
		// 计算
		this.pageCount = (this.pageSize + this.rowCount -1) / this.pageSize ;
//		this.pageCount = this.rowCount % this.pageSize ==0? this.rowCount/this.pageSize:this.rowCount/this.pageSize+1;
		// 只显示3个页码
		if(this.pageCount <=3) {
			this.startIndex = 1 ;
			this.endIndex = this.pageCount ;
		} else {
			// 当前的页码，超过了 3页
			this.startIndex = this.pageNo -1 ;
			this.endIndex = this.pageNo + 1 ;
			// 你不停的点上一页
			if(this.startIndex <= 1) {
				this.startIndex = 1 ;
				this.endIndex = 3 ;
			}
			if(this.endIndex >= this.pageCount) {
				this.endIndex = this.pageCount ;
				this.startIndex = this.endIndex -2 ;
			}
		}
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	
	
	
	
}
