package com.edu.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.edu.bean.PageBean;
import com.edu.bean.User;
import com.edu.contant.Constant;
import com.edu.service.UserService;
import com.edu.service.impl.UserServiceImpl;
import com.edu.util.MD5Util;
import com.edu.util.UUIDUtil;

/**
 * Servlet implementation class UserManagerServlet
 */
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 处理中文乱码（post）
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
		} else if("delete".equals(method)) {
			delete(request,response);
		}
	}
	/**
	 * 用户展示用户列表的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int pageNo = 1 ;
		try {
			pageNo = Integer.valueOf(request.getParameter("pageNo")) ;
		} catch(Exception e) {
		}
		try {
			// 准备数据
			UserService userService = new UserServiceImpl() ;
			PageBean pageBean = userService.getPageBean(pageNo,Constant.PAGESIZE);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/user/list.jsp").forward(request, response);
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
		request.getRequestDispatcher("/WEB-INF/jsp/manager/user/addUI.jsp").forward(request, response);
		
	}
	/**
	 * 进行新增的操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// 要封装数据
			User user = new User() ;
			BeanUtils.copyProperties(user, request.getParameterMap());
			// 譬如id
			// 譬如密码需要加密
			user.setId(UUIDUtil.getId());
			user.setPwd(MD5Util.getMD5(user.getPwd()));
			user.setActive(Constant.ACTIVE);
			UserService userService = new UserServiceImpl() ;
			userService.saveUser(user);
			response.sendRedirect(request.getContextPath()+"/manager/user?method=list");
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
			String id = request.getParameter("id");
			UserService userService = new UserServiceImpl() ;
			User user = userService.getUserById(id);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/user/updateUI.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 进行修改操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// 要封装数据
			User user = new User() ;
			BeanUtils.copyProperties(user, request.getParameterMap());
			user.setPwd(MD5Util.getMD5(user.getPwd()));
			// 此处密码一定要加密
			UserService userService = new UserServiceImpl() ;
			userService.update(user);
			response.sendRedirect(request.getContextPath()+"/manager/user?method=list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			UserService userService = new UserServiceImpl() ;
			userService.deleteById(id);
			response.sendRedirect(request.getContextPath()+"/manager/user?method=list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
