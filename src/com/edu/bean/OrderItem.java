package com.edu.bean;
/**
 * 这叫做订单项
 * @author daixianren
 *
 */
public class OrderItem {
	
	private Product product ;
	private Integer num ;// 商品的购买数量
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
	
}
