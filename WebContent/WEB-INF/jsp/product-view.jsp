<%@page import="com.sun.org.apache.bcel.internal.generic.FNEG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城 - 首页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/font/iconfont.css"/>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/scripts/function.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/footer.js"></script>
</head>
<body>
<!--静态引入  -->
<%@ include file="common/header.jsp" %>
<div id="childNav" class="bg_childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="main" class="wrap">
	<!-- 采用静态引入 -->
	<%@ include file="common/left.jsp" %>
		<div id="product" class="main">
		<h1>${product.title }</h1>
		<div class="infos">
			<div class="thumb"><img src="${product.pic }" width="200px" height="200px"/></div>
			<div class="buy">
				<p>商城价：<span class="price">￥${product.price }</span></p>
				<p>库　存：${product.stock }</p>
				<p>库　存：有货</p>
				<p>库　存：有货</p>
				<form action="${pageContext.request.contextPath}/cart" method="post">
					<input type="hidden" name="method" value="addToCart"/>
				    <input type="hidden" name="id" value="${product.id }"/> 
					<p>购买数量：<input type="text" name="num" value="1" style="height:20px;width:40px;text-align: center;"></p>
					<div class="button">
						<input type="submit" name="button" value=""  />
						<!-- <a href="#">放入购物车</a> -->
					</div>
				</form>
			</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				sdf<br />
				sdf<br />
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
