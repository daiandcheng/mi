<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理 - 商城</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function-manage.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="${pageContext.request.contextPath}/images/logo.png" style="width:230px;height: 60px;" /></div>
	<div class="help"><a href="#" class="shopping">购物车</a><a href="login.html">登录</a><a href="register.html">注册</a><a href="guestbook.html">留言</a></div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员pillys您好，今天是2012-12-21，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">商城</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><em><a href="${pageContext.request.contextPath}/manager/user?method=addUI">新增</a></em>
				<a href="${pageContext.request.contextPath}/manager/user?method=list">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="${pageContext.request.contextPath}/manager/category?method=addUI">新增</a></em>
				<a href="${pageContext.request.contextPath}/manager/category?method=list">分类管理</a></dd>
				<dd><em><a href="product-add.html">新增</a></em><a href="product.html">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.html">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.html">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.html">新增</a></em><a href="news.html">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>性别</th>
					<th>Email</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				
				<c:forEach var="u" items="${pageBean.data }">
					<tr>
						<td class="first w4 c">${u.id }</td>
						<td class="w1 c">${u.name }</td>
						<td class="w2 c">${u.sex==1?'男':'女'}</td>
						<td>${u.email }</td>
						<td class="w4 c">${u.phone }</td>
						<td class="w1 c">
						<a href="${pageContext.request.contextPath}/manager/user?method=updateUI&id=${u.id}">修改</a> 
						<a href="${pageContext.request.contextPath}/manager/user?method=delete&id=${u.id}">删除</a>
					    </td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">
						当前${pageBean.pageNo }页/总共${pageBean.pageCount }页 每页${pageBean.pageSize }条总共${pageBean.rowCount }条记录
						
					</td>
					<td colspan="4">
						<a href="${pageContext.request.contextPath}/manager?method=list&pageNo=1">首页</a>
						<c:if test="${pageBean.pageNo>1 }">
							<a href="${pageContext.request.contextPath}/manager?method=list&pageNo=${pageBean.pageNo-1}">上一页</a>
						</c:if>
						<c:forEach var="p" begin="${pageBean.startIndex }" end="${pageBean.endIndex }">
						   <c:if test="${p == pageBean.pageNo }">
								<a href="javascript:void(0);">${p }</a>&nbsp;&nbsp;
						   </c:if>
						   <c:if test="${p != pageBean.pageNo }">
								<a href="${pageContext.request.contextPath}/manager?method=list&pageNo=${p}">${p }</a>&nbsp;&nbsp;
						   </c:if>
						</c:forEach>
						<c:if test="${pageBean.pageNo<pageCount }">
							<a href="${pageContext.request.contextPath}/manager?method=list&pageNo=${pageBean.pageNo+1}">下一页</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/manager?method=list&pageNo=${pageBean.pageCount}">末页</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 MSTF All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
