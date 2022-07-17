package com.edu.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.edu.bean.Msg;
import com.edu.bean.User;
import com.edu.contant.Constant;
import com.edu.service.UserService;
import com.edu.service.impl.UserServiceImpl;
import com.edu.util.JacksonUtil;
import com.edu.util.MD5Util;
import com.edu.util.UUIDUtil;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if("registerUI".equals(method)) {
			registerUI(request,response);
		} else if("register".equals(method)) {
			register(request,response);
		} else if("active".equals(method)) {
			active(request,response);
		} else if("loginUI".equals(method)) {
			loginUI(request,response);
		} else if("checkUser".equals(method)) {
			checkUser(request,response);
		} else if("login".equals(method)) {
			login(request,response);
		} else if("loginOut".equals(method)) {
			loginOut(request,response);
		}
	}
	/**
	 * 注销操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void loginOut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getSession().invalidate(); // session失效
		response.sendRedirect(request.getContextPath()+"/");
	}
	/**
	 * 用户登录操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			String veryCode = request.getParameter("veryCode") ;
			String safeCode = (String)request.getSession().getAttribute("safeCode");
			// remeberMe
			String remeberMe = request.getParameter("remeberMe");
			if(!veryCode.equals(safeCode)) {
				request.setAttribute("msg", "你的验证码有问题");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			// 拿着用户名去找用户,调用service
			UserService userService = new UserServiceImpl() ;
			User user = userService.getUser(name) ;
			if(null == user) {
				request.setAttribute("msg", "用户名或者密码错误");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			// 数据库中有这个用户,判断密码是否一致
			String ectype = MD5Util.getMD5(pwd) ;
			if(!user.getPwd().equals(ectype)) {
				request.setAttribute("msg", "用户名或者密码错误");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			// 判断用户是否激活
			if(user.getActive() != Constant.ACTIVE) {
				request.setAttribute("message", "用户没有激活，请去邮箱完成激活操作");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return ;
			}
			// 勾选了记住了用户名
			if("remeberMe".equals(remeberMe)) {
				// 把用户名保存到客户端的浏览器的cookie中
				// 1. 创建一个cookie
				Cookie cookie = new Cookie("USERCOOKIE",URLEncoder.encode(name, "UTF-8"));
				// 2. 这是cookie的有效期
				cookie.setMaxAge(60*60*30);
				// 3. cookie保存的路径
				cookie.setPath(request.getContextPath()+"/");
				// 4. 把cookie写入到客户端的浏览器中
				response.addCookie(cookie);
			}
			
			// 代表登录成功
			request.getSession().setAttribute("USER", user);
			// 重定向到首页 http://localhost:8080/mi/
			response.sendRedirect(request.getContextPath()+"/");				
		} catch (Exception e) {
			request.setAttribute("message", "当前用户操作失败，失败信息为:"+e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			e.printStackTrace();
		}
 		
		
	}
	/**
	 * 校验用户名
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void checkUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("name");
		UserService userService = new UserServiceImpl() ;
		try {
			Msg msg = userService.getUserByName(name);
			// 要把这个对象转换成json格式的字符串 我们使用jackson
			String result = JacksonUtil.convertObjectToString(msg);
			// 这个字符串，怎么响应的问题
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter() ;
			out.write(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 这是跳转到登录页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void loginUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	/**
	 * 激活方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void active(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String code = request.getParameter("code");
			UserService userService = new UserServiceImpl();
			userService.active(code);
			// 跳转到登录页面
			response.sendRedirect(request.getContextPath()+"/user?method=loginUI");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "当前用户保存失败,失败信息为:"+e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
	/**
	 * 进行注册操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			User user = new User();	
			BeanUtils.copyProperties(user, request.getParameterMap());
			// 补全数据  id code  uuid
			user.setId(UUIDUtil.getId());
			user.setCode(UUIDUtil.getCode());
			// active 
			user.setActive(Constant.UN_ACTIVE);
			// 密码要经过加密 MD5 加密，这个算法是不可逆
			user.setPwd(MD5Util.getMD5(user.getPwd()));
			// 调用service 来进行数据保存操作
			UserService userService = new UserServiceImpl();
			userService.save(user);
			request.getSession().setAttribute("message", "当前用户已经注册成功,请去邮箱激活");
			response.sendRedirect(request.getContextPath()+"/message.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "当前用户保存失败,失败信息为:"+e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	
	}
	/**
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void registerUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

}
