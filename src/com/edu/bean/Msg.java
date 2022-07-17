package com.edu.bean;

public class Msg<T> {
	private Integer code ; // 200 代表成功,500代表失败
	private String msg ; // 响应的信息
	private T data ;// 响应的数据
	
	
	
	public Msg(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Msg(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
