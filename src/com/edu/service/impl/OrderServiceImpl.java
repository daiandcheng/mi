package com.edu.service.impl;

import java.util.List;

import com.edu.bean.Cart;
import com.edu.bean.Order;
import com.edu.bean.User;
import com.edu.dao.OrderDao;
import com.edu.dao.impl.OrderDaoImpl;
import com.edu.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public Order createOrder(Cart cart,User user) throws Exception {
		// 创建一个订单，向订单表中添加一条记录，向订单项表中添加多条记录
		// 这个操作需要在一个事务中完成。
		OrderDao orderDao = new OrderDaoImpl();
		
		return orderDao.save(cart,user);
		
	}

	@Override
	public Order getOrderById(String oid) throws Exception {
		OrderDao orderDao = new OrderDaoImpl();
		
		return orderDao.getOrderById(oid);
	}

	@Override
	public List<Order> getOrderByUser(String uid) throws Exception {
		// TODO Auto-generated method stub
		OrderDao orderDao = new OrderDaoImpl();
		return  orderDao.getOrderByUser(uid);
	}

	@Override
	public void update(Order order) throws Exception {
		OrderDao orderDao = new OrderDaoImpl();
		orderDao.update(order);
	}

	@Override
	public void updateOrderById(String out_trade_no) throws Exception {
		OrderDao orderDao = new OrderDaoImpl();
		orderDao.updateOrderById(out_trade_no);
		
	}

}
