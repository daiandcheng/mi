package com.edu.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.Advice;
import com.edu.bean.News;
import com.edu.service.AdviceService;
import com.edu.service.NewsService;
import com.edu.service.impl.AdviceServiceImpl;
import com.edu.service.impl.NewsServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if("index".equals(method)) {
			index(request,response);
		}
	}
	/**
	 * 跳转到首页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 准备公告的数据 最新7条数据
		try {
			AdviceService adviceService = new AdviceServiceImpl();
			List<Advice> adviceList = adviceService.getLatestData();
			// 准备新闻的数据 最新7条数据
			NewsService newsService = new NewsServiceImpl();
			List<News> newsList = newsService.getLatestData();
			request.setAttribute("adviceList", adviceList);
			request.setAttribute("newsList", newsList);
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "首页显示失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

}
