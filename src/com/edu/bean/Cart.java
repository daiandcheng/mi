package com.edu.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 这是购物车的实体对象
 * @author daixianren
 *
 */
public class Cart {
	// 购物车
	private Map<String,CartItem> carts = new HashMap<String,CartItem>() ;
	private  double total = 0.0 ;
	
	/**
	 * 获取所有的购物项的集合
	 * @return
	 */
	public Collection<CartItem> getCartItems() {
		
		return carts.values() ;
	}
	/**
	 * 向购物车中添加一个购物项
	 * @param cartItem
	 */
	public void add(CartItem cartItem) {
		// 1. 判断当前的购物项在不在购物车中
		if(carts.containsKey(cartItem.getProduct().getId())) {
			// 在，更新数量，和总金额
			CartItem oldCartItem = carts.get(cartItem.getProduct().getId());
			oldCartItem.setNum(oldCartItem.getNum() + cartItem.getNum());
			// 总金额
		} else {
			// 不在,添加到购物车中，计算总金额
			carts.put(cartItem.getProduct().getId(), cartItem) ;
		}
		total += cartItem.getSubtotal() ;
	}
	/**
	 * 根据商品的id来移除一个购物项
	 * @param pid
	 */
	public void remove(String pid) {
		CartItem cartItem = carts.remove(pid);
		// 更新总金额
		total -= cartItem.getSubtotal() ;
	}
	/**
	 * 清空购物车
	 */
	public void clear() {
		carts.clear(); 
		total = 0.0 ;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
