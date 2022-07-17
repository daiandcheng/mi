package com.edu.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.service.CategoryService;
import com.edu.service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method") ;
		if("list".equals(method)) {
			list(request,response);
		}
	}
	/**
	 * 找所有的分类，把所有的分类转换成json数据格式，响应到客户端
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			CategoryService categoryService = new CategoryServiceImpl();
			String result = categoryService.getAll();
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter() ;
			out.println(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "查询所有的分类信息出现错误");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

}
