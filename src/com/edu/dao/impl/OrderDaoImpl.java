package com.edu.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.edu.bean.Cart;
import com.edu.bean.CartItem;
import com.edu.bean.Order;
import com.edu.bean.OrderItem;
import com.edu.bean.Product;
import com.edu.bean.User;
import com.edu.contant.Constant;
import com.edu.dao.OrderDao;
import com.edu.util.DataSourceUtil;
import com.edu.util.UUIDUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public Order save(Cart cart,User user) throws Exception {
		// 向订单表中添加一条记录，向订单项表中添加多条记录
		try {
			DataSourceUtil.startTransaction(); // 开启事务
			QueryRunner queryRunner = new QueryRunner() ;
			// 向 订单表中，添加一条数据
			String oid = UUIDUtil.getId() ;
			String sql ="insert into tbl_order(id,total,order_date,state,uid) values(?,?,?,?,?)" ;
			queryRunner.update(DataSourceUtil.getConnection(), //
					sql,oid,cart.getTotal(),new Date(),Constant.UN_PAY,user.getId()) ;
//			int result = 1 / 0 ;
			// 接下来向订单项表中添加多条记录，需要订单的id,商品的id,订购的数量
			Collection<CartItem> cartItems = cart.getCartItems() ;
			for(CartItem cartItem:cartItems) {
				sql = "insert into tbl_order_product(oid,pid,num) values(?,?,?)";
				queryRunner.update(DataSourceUtil.getConnection(), //
						sql,oid,cartItem.getProduct().getId(),cartItem.getNum()) ;
			}
			// 没有出错
			DataSourceUtil.commitAndReleased(); // 提交事务
			Order order = new Order();
			order.setId(oid);
			return order ;
		} catch (Exception e) {
			DataSourceUtil.rollback(); // 回滚事务
		} 
		return null;
	}
	public static void main(String[] args) throws Exception {
		new OrderDaoImpl().getOrderById("5917d319bff348bab1af9d2519b55c17");
	}
	@Override
	public Order getOrderById(String oid) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		Order order = query.query("select * from tbl_order where id=? order by order_date desc", //
				new BeanHandler<Order>(Order.class),oid);
		// 如果这样子写的话，只有订单信息，没有订单项信息
		// 把这个订单下面所有的订单项查询出来。订单项目中，包含商品信息和数量
		List<Product> products = query.query("select * from tbl_product where id in(select pid from tbl_order_product where oid = ?)", 
				new BeanListHandler<Product>(Product.class), order.getId());
		List<OrderItem> orderItems = new ArrayList<OrderItem>() ;
		for(Product product: products) {
			OrderItem orderItem = new OrderItem() ;
			orderItem.setProduct(product);
			Integer num = (Integer) query.query("select num from tbl_order_product where oid=? and pid=?", 
					new ScalarHandler(),order.getId(),product.getId());
			orderItem.setNum(num);
			orderItems.add(orderItem) ;
		}
		
		order.setOrderItems(orderItems);
		return order;
	}
	@Override
	public List<Order> getOrderByUser(String uid) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		// 这里面只有订单的信息
		List<Order> orderList = query.query("select * from tbl_order where uid=? order by order_date desc", 
				new BeanListHandler<Order>(Order.class),uid);
		// 设置每个订单里面的订单项
		for(Order order:orderList) {
			List<Product> products = query.query("select * from tbl_product where id in(select pid from tbl_order_product where oid = ?)", 
					new BeanListHandler<Product>(Product.class), order.getId());
			List<OrderItem> orderItems = new ArrayList<OrderItem>() ;
			for(Product product: products) {
				OrderItem orderItem = new OrderItem() ;
				orderItem.setProduct(product);
				Integer num = (Integer) query.query("select num from tbl_order_product where oid=? and pid=?", 
						new ScalarHandler(),order.getId(),product.getId());
				orderItem.setNum(num);
				orderItems.add(orderItem) ;
			}
			order.setOrderItems(orderItems);
		}
	
		return orderList;
	}
	@Override
	public void update(Order order) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "update tbl_order set name=?,address=?,phone=? where id=?" ;
		query.update(sql,order.getName(),order.getAddress(),order.getPhone(),order.getId()) ;
	}
	@Override
	public void updateOrderById(String out_trade_no) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "update tbl_order set state=? where id=?" ;
		query.update(sql,Constant.PAY,out_trade_no) ;
		
	}

}
