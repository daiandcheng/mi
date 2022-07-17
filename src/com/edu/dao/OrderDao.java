package com.edu.dao;

import java.util.List;

import com.edu.bean.Cart;
import com.edu.bean.Order;
import com.edu.bean.User;

public interface OrderDao {

	Order save(Cart cart,User user) throws Exception ;

	Order getOrderById(String oid) throws Exception ;

	List<Order> getOrderByUser(String uid) throws Exception ;

	void update(Order order) throws Exception ;

	void updateOrderById(String out_trade_no) throws Exception ;

}
