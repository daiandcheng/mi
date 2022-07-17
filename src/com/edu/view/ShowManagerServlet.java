package com.edu.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.Pie;
import com.edu.service.PieService;
import com.edu.service.impl.PieServiceImpl;
import com.edu.util.JacksonUtil;

/**
 * Servlet implementation class ShowManagerServlet
 */
public class ShowManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method") ;
		if("category".equals(method)) {
			category(request,response);
		}
	}

	private void category(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PieService pieService = new PieServiceImpl() ;
		String datas = JacksonUtil.convertObjectToString(pieService.getPieList()) ;
		request.setAttribute("datas", datas);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/show/category.jsp").forward(request, response);
	}

}
