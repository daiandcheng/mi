package com.edu.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.Cart;
import com.edu.bean.CartItem;
import com.edu.bean.Product;
import com.edu.service.ProductService;
import com.edu.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method") ;
		if("addToCart".equals(method)) {
			addToCart(request,response);
		} else if("list".equals(method)) {
			list(request,response);
		} else if("del".equals(method)) {
			del(request,response);
		}else if("clear".equals(method)) {
			clear(request,response);
		}
	}
	private void clear(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Cart cart = getCart(request) ;
		cart.clear();
		response.sendRedirect(request.getContextPath()+"/cart?method=list");
	}
	private void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id") ;
		Cart cart = getCart(request) ;
		cart.remove(id);
		response.sendRedirect(request.getContextPath()+"/cart?method=list");
	}
	/**
	 * 跳转到购物车列表页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp").forward(request, response);
	}
	/**
	 * 添加购物项，到购物车中
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addToCart(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// 获取商品id
			String id = request.getParameter("id") ;
			// 获取购买数量
			int num = Integer.valueOf(request.getParameter("num")) ;
			// 调用商品的service，根据id，获取当前的商品
			ProductService productService = new ProductServiceImpl() ;
			Product product = productService.getProductById(id) ;
			// 封装一个购物项
			CartItem cartItem = new CartItem(product,num);
			// 购物项要放入到购物车中，购物车从哪里来？
			Cart cart = getCart(request);
			cart.add(cartItem);
			response.sendRedirect(request.getContextPath()+"/cart?method=list");
//			request.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart") ;
		if(cart == null) {
			 cart = new Cart() ;
			 request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

}
