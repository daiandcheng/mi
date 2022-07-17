package com.edu.bean;
/**
 * 这是饼图
 * @author daixianren
 *
 */
public class Pie {
	private String name;
	private int value ;
	public Pie() {}
	public Pie(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
