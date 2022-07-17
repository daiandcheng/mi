package com.edu.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.Product;
import com.edu.contant.Constant;
import com.edu.service.ProductService;
import com.edu.service.impl.ProductServiceImpl;
import com.edu.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method") ;
		if("pro".equals(method)) {
			// 特价的数据
			pro(request,response);
		} else if("hot".equals(method)) {
			// 热卖的数据
			hot(request,response);
		} else if("detail".equals(method)) {
			detail(request,response);
		}
	}
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			ProductService productService = new ProductServiceImpl() ;
			Product product = productService.getProductById(id);
			// 最近浏览的商品
			addProductToCookie(product,request,response);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/WEB-INF/jsp/product-view.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加商品到cookie中
	 * @param product
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addProductToCookie(Product product, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 先从cookie中去找数据
		String cookie_item_value = null;
		Cookie[] cookies = request.getCookies() ;
		for(Cookie cookie:cookies) {
			if("cookie_item_product".equals(cookie.getName())) {
				cookie_item_value = cookie.getValue() ;
				break ;
			}
		}
		// 有没有cookie
		if(null == cookie_item_value || cookie_item_value.trim().length() == 0) {
			LinkedList<Product> linkedList = new LinkedList<Product>() ;
			linkedList.add(product) ;
			// 把这个商品写入到cookie
			Cookie cookie = new Cookie("cookie_item_product",URLEncoder.encode(JacksonUtil.convertObjectToString2(linkedList), "UTF-8"));
			cookie.setMaxAge(60*60*6);
			cookie.setPath(request.getContextPath()+"/");
			// 把这个cookie写入到浏览器
			response.addCookie(cookie);
		} else {
			// 有数据，我要判断当前的数据，是否在cookie中
			//把这 cookie_item_value 转换成 LinkedList<Product> Jackson
			ObjectMapper mapper = new ObjectMapper();
			LinkedList<Product>  products = mapper.readValue(URLDecoder.decode(cookie_item_value, "UTF-8"), 
					new TypeReference<LinkedList<Product>>() {}) ;
			// 判断当前的商品，是否在在cookie中
			if(products.contains(product)) {
				// 在,先移除，再添加到头部
				products.remove(product) ;
			} else {
				// 判断cookie中的数据，是或>=2
				if( products.size() >=2) {
					products.removeLast() ; // 移除最后
				}
			}
			products.addFirst(product); // 添加新的到头部
			Cookie cookie = new Cookie("cookie_item_product",URLEncoder.encode(JacksonUtil.convertObjectToString2(products), "UTF-8"));
			cookie.setMaxAge(60*60*6);
			cookie.setPath(request.getContextPath()+"/");
			// 把这个cookie写入到浏览器
			response.addCookie(cookie);
		}
	}
	private void hot(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try {
			ProductService productService = new ProductServiceImpl() ;
			// 1代表热销
			List<Product> productList = productService.getProduct(1);
			PrintWriter out = response.getWriter() ;
			out.println(JacksonUtil.convertObjectToString(productList));
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try {
			ProductService productService = new ProductServiceImpl() ;
			// 2.代表特价
			List<Product> productList = productService.getProduct(2);
			PrintWriter out = response.getWriter() ;
			out.println(JacksonUtil.convertObjectToString(productList));
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
