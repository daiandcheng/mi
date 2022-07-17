package com.edu.bean;
/**
 * 购物项
 * @author daixianren
 *
 */
public class CartItem {
	// 商品信息
	private Product product ;
	// 购买信息
	private int num ;// 购买数量
	// 小计
	private double subtotal ;// 小计
	
	public CartItem() {}
	
	public CartItem(Product product, int num) {
		this.product = product;
		this.num = num;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubtotal() {
		return this.product.getPrice() * this.num;
	}
	
	

}
