package com.edu.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.Category;
import com.edu.service.CategoryService;
import com.edu.service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryManagerServlet
 */
public class CategoryManagerServlet extends HttpServlet {
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
		} else if("addUI".equals(method)) {
			addUI(request,response);
		} else if("add".equals(method)) {
			add(request,response);
		} else if("updateUI".equals(method)) {
			updateUI(request,response);
		} else if("update".equals(method)) {
			update(request,response);
		} else if("del".equals(method)) {
			del(request,response);
		}
	}
	/**
	 * 列表展示
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			CategoryService categoryService = new CategoryServiceImpl() ;
			List<Category> categoryList = categoryService.getBigCategory();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/category/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到新增页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// 准备所有的父类的数据
			CategoryService categoryService = new CategoryServiceImpl() ;
			List<Category> categoryList = categoryService.getParentCategory();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/category/addUI.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 进行新增操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			CategoryService categoryService = new CategoryServiceImpl() ;
			String pidStr = request.getParameter("pid") ;
			Integer pid = null ; 
			if(pidStr != null && pidStr.trim().length() >0) {
				pid = Integer.valueOf(pidStr) ;// 父类的id
			}
			
			String name = request.getParameter("name");
			Category category = new Category();
			category.setPid(pid);
			category.setName(name);
			categoryService.save(category);
			response.sendRedirect(request.getContextPath()+"/manager/category?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 跳转到修改页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));// 分类的id
			// 准备所有的父类的数据
			CategoryService categoryService = new CategoryServiceImpl() ;
			List<Category> categoryList = categoryService.getParentCategory();
			Category category = categoryService.getById(id);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("category", category);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/category/updateUI.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 *  进行修改操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String pidStr = request.getParameter("pid") ;
			Integer pid = null ; 
			if(pidStr != null && pidStr.trim().length() >0) {
				pid = Integer.valueOf(pidStr) ;// 父类的id
			}
			Integer id = Integer.valueOf(request.getParameter("id"));// 分类的id
			String name = request.getParameter("name");
			Category category = new Category();
			category.setId(id);
			category.setPid(pid);
			category.setName(name);
			CategoryService categoryService = new CategoryServiceImpl() ;
			categoryService.update(category);
			response.sendRedirect(request.getContextPath()+"/manager/category?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 进行删除操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));// 分类的idCategoryService categoryService = new CategoryServiceImpl() ;
			CategoryService categoryService = new CategoryServiceImpl() ;
			categoryService.deleteById(id);
			response.sendRedirect(request.getContextPath()+"/manager/category?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
