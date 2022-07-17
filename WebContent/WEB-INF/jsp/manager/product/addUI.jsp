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
		<h2>新增用户</h2>
		<div class="manage">
			<form method="post" action="${pageContext.request.contextPath}/manager/product?method=add" enctype="multipart/form-data">
				<table class="form">
					<tr>
						<td class="field">商品名称：</td>
						<td>
							<input type="text" class="text" name="title" value="" />
						</td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
							<select name="category_id">
							   <c:forEach var="parent" items="${categoryList }">
							   		<option value="${parent.id }">${parent.name }</option>
							   		<c:forEach var="children" items="${parent.childrens }" varStatus="status">
							   			<c:if test="${status.index == parent.size-1 }">
							   				<option value="${children.id }">└ ${children.name }</option>
							   			</c:if>
							   			<c:if test="${status.index != parent.size-1 }">
								   			<option value="${children.id }">├ ${children.name }</option>
							   			</c:if>
							   		</c:forEach>
							   </c:forEach>
								
								<!-- <option value="3">├ 电器</option>
								<option value="3">└ 电器</option> -->
								
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">商品图片：</td>
						<td><input type="file" class="text" name="file" /></td>
					</tr>
					<tr>
						<td class="field">商品价格：</td>
						<td><input type="text" class="text tiny" name="price" /> 元</td>
					</tr>
					<tr>
						<td class="field">品牌：</td>
						<td>
							<select name="brand_id">
							   <c:forEach var="b" items="${brandList }">
							   		<option value="${b.id }">${b.name }</option>
							
							   </c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">是否促销：</td>
						<td>
							<input type="radio" name="pro_price" value="1" checked="checked"> 促销
							<input type="radio" name="pro_price" value="0"> 非促销
						</td>
					</tr>
					<tr>
						<td class="field">是否热卖：</td>
						<td>
							<input type="radio" name="hot_recommand" value="1" checked="checked"> 推荐
							<input type="radio" name="hot_recommand" value="0"> 不推荐
						</td>
					</tr>
					<tr>
						<td class="field">是否下架：</td>
						<td>
							<input type="radio" name="product_off" value="1" checked="checked"> 上架
							<input type="radio" name="product_off" value="0"> 下架
						</td>
					</tr>
					<tr>
						<td class="field">库存：</td>
						<td><input type="text" class="text tiny" name="stock" /></td>
					</tr>
					<tr>
						<td class="field">条码号：</td>
						<td><input type="text" class="text" name="code" /></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="添加" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 MSTF All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
