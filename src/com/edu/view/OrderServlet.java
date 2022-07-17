package com.edu.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.Cart;
import com.edu.bean.Order;
import com.edu.bean.User;
import com.edu.service.OrderService;
import com.edu.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method") ;
		if("createOrder".equals(method)) {
			createOrder(request,response);
		} else if("detail".equals(method)) {
			detail(request,response);
		} else if("list".equals(method)) {
			list(request,response);
		}
	}
	/**
	 * 我的订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// 根据用户的编号去查询
			// 调用service获取订单
			String uid = request.getParameter("uid") ;
			OrderService orderService = new OrderServiceImpl();
			List<Order> orderList = orderService.getOrderByUser(uid);
			request.setAttribute("orderList", orderList);
			request.getRequestDispatcher("/WEB-INF/jsp/order-my.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 这是订单详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String oid = request.getParameter("oid") ;
			// 调用service获取订单
			OrderService orderService = new OrderServiceImpl();
			Order order = orderService.getOrderById(oid);
			// 把订单保存到request作用域中,转发到订单详情页面
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/jsp/order-detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建订单的过程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createOrder(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// 创建订单，要把购物车里面的购物项保存到数据库中，要调用订单的service ,进行订单的保存
			// 从session中去拿购物车
			Cart cart = (Cart) request.getSession().getAttribute("cart") ;
			OrderService orderService = new OrderServiceImpl();
			User user = (User) request.getSession().getAttribute("USER");
			Order order = orderService.createOrder(cart,user);
			// 跳转到订单详情页面中
			response.sendRedirect(request.getContextPath()+"/order?method=detail&oid="+order.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
