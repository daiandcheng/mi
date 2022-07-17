package com.edu.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是商品分类的实体bean
 * @author daixianren
 *
 */
public class Category {
	private Integer id ;
	private String name;
	private Integer pid ;
	// 这是当前分类下面的二级分类
	private List<Category> childrens = new ArrayList<Category>();
	
	public Category(){}
	
	public Category(String name, Integer pid) {
		this.name = name;
		this.pid = pid;
	}
	
	public Category(Integer id, String name, Integer pid) {
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public List<Category> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Category> childrens) {
		this.childrens = childrens;
	}
	
	public int getSize() {
		return childrens.size() ;
	}
	

}
