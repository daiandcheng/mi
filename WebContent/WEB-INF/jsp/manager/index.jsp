<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<dd><em><a href="user-add.html">新增</a></em><a href="user.html">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="productClass-add.html">新增</a></em>
				<a href="${pageContext.request.contextPath}/manager/category?method=list">分类管理</a></dd>
				<dd><em><a href="product-add.html">新增</a></em><a href="product.html">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.html">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.html">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.html">新增</a></em><a href="news.html">新闻管理</a></dd>
				<dt>统计分析</dt>
				<dd>
					<a href="${pageContext.request.contextPath}/manager/show?method=category">分类展示</a>
				</dd>
				<dd>
					<a href="guestbook.html">销售量统计</a>
				</dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>管理首页</h2>
		<div id="welcome" class="manage">
			<div class="shadow">
				<em class="corner lb"></em>
				<em class="corner rt"></em>
				<div class="box">
					<div class="msg">
						<p>欢迎回来</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 MSTF All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>