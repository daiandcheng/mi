package com.edu.bean;
/**
 * 新闻实体bean
 * @author daixianren
 *
 */
public class News {
	private Integer id ;
	private String title ;
	private String content ;
	private String pub ;
	
	public News() {}
	public News(Integer id, String title, String content, String pub) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.pub = pub;
	}
	public News(String title, String content, String pub) {
		this.title = title;
		this.content = content;
		this.pub = pub;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	
	
}
