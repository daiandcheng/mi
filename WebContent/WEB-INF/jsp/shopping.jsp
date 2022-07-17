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
<div class="wrap">
   <c:if test="${empty cart or empty cart.cartItems}">
	<h1>你购物为空~~~~~~~~~~~~~~~~~~~~~~~~~~~~</h1>   
   </c:if>
   <c:if test="${not empty cart and not empty cart.cartItems}">
   	<div id="shopping">
		<form action="${pageContext.request.contextPath}/order" method="post">
			<input type="hidden" name="method" value="createOrder">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>小计</th>
					<th>操作</th>
				</tr>
				<c:forEach var="item" items="${cart.cartItems}">
					<tr id="product_id_1">
						<td class="thumb">
							<img src="${item.product.pic }" width="100px" height="50px" />
							<a href="product-view.html">${item.product.title }</a>
						</td>
						<td class="price" id="price_id_1">
							<span>￥${item.product.price }</span>
						</td>
						<td class="number">
							${item.num }
						</td>
						<td class="number">
						   <fmt:formatNumber minFractionDigits="2">${item.subtotal }</fmt:formatNumber>
							
						</td>
						<td class="delete"><a href="${pageContext.request.contextPath}/cart?method=del&id=${item.product.id}">删除</a></td>
					</tr>
			    </c:forEach>
			</table>
			<div class="button">
					总金额:￥<fmt:formatNumber minFractionDigits="2">${cart.total }</fmt:formatNumber>
					<a href="${pageContext.request.contextPath}/cart?method=clear">清空购物车</a>
				<input type="submit" value="" />
			
			</div>
		</form>
	</div>
   </c:if>
</div>
<div id="footer">
	Copyright &copy; 2010 MSTF All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
