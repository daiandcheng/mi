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
		<h2>分类管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>分类名称</th>
					<th>操作</th>
				</tr>
				<c:forEach var="bigCategory" items="${categoryList }">
					<tr>
						<td class="first w4 c">${bigCategory.id }</td>
						<td>${bigCategory.name }</td>
						<td class="w1 c">
							<a href="${pageContext.request.contextPath}/manager/category?method=updateUI&id=${bigCategory.id}">修改</a> 
							<a href="${pageContext.request.contextPath}/manager/category?method=del&id=${bigCategory.id}">删除</a>
						</td>
					</tr>
					<c:forEach var="smallCategory" items="${bigCategory.childrens }">
						<tr>
							<td class="first w4 c">${smallCategory.id }</td>
							<td class="childClass">${smallCategory.name }</td>
							<td class="w1 c">
							<a href="${pageContext.request.contextPath}/manager/category?method=updateUI&id=${smallCategory.id}">修改</a> 
							<a href="${pageContext.request.contextPath}/manager/category?method=del&id=${smallCategory.id}">删除</a></td>
						</tr>
					</c:forEach>
				</c:forEach>
				
				
				
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
