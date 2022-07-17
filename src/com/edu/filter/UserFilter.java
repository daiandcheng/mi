package com.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.User;

public class UserFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request ;
		HttpServletResponse res = (HttpServletResponse) response;
		// 要判断用户是否登录
		User user = (User) req.getSession().getAttribute("USER") ;
		// 如果用户没有登录，不能对购物车，和订单操作
		if(user == null) {
			req.setAttribute("msg", "用户尚未登录..................");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
			return ;
		}
		
		// 如果用户已经登录，则放行
		chain.doFilter(req, res);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
