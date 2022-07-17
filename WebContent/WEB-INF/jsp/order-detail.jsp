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
  
   	<div id="shopping">
		<form action="${pageContext.request.contextPath}/pay" method="post">
			<input type="hidden" name="method" value="pay">
			<input type="hidden" name="oid" value="${order.id }">
			<input type="hidden" name="total" value="<fmt:formatNumber minFractionDigits='2'>${order.total }</fmt:formatNumber>">
			<table>
				<tr>
					<th colspan="4">
						<h2>当前订单的编号:${order.id },订单的总金额:${order.total },订单的状态:${order.state==0?"未支付":"已支付" }</h2>
					</th>
					
				</tr>
				<c:forEach var="item" items="${order.orderItems}">
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
						   <fmt:formatNumber minFractionDigits="2">${item.product.price*item.num }</fmt:formatNumber>
							
						</td>
						
					</tr>
			    </c:forEach>
			</table>
		<c:if test="${order.state==0}">
		
			<p>收件人姓名: <input type="text" name="name" /></p>
			<p>收件人地址: <input type="text" name="address" /></p>
			<p>收件人电话: <input type="text" name="phone" /></p>
			
			<div class="button">
				
					
				<input type="submit" value="" />
			
			</div>
		</c:if>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2010 MSTF All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
