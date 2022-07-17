<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城 - 首页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/font/iconfont.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/register.js"></script>
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
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册商城</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写注册信息</li>
				<li class="last"><em></em>注册成功</li>
			</ul>
			<form id="regForm" method="post" action="${pageContext.request.contextPath}/user">
				<input type="hidden" name="method" value="register"/>
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="name"/><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="pwd"/><span></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="text" type="password" name="rePassWord"/><span></span></td>
					</tr>
					<tr>
						<td class="field">手机：</td>
						<td><input class="text" type="text" name="phone"/><span></span></td>
					</tr>
					<tr>
						<td class="field">邮箱：</td>
						<td><input class="text" type="text" name="email"/><span></span></td>
					</tr>
					<tr>
						<td class="field">生日：</td>
						<td><input class="text" type="text" name="birthday"/><span></span></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td>
							<input type="radio" name="sex" value="1" checked> 男
							<input type="radio" name="sex" value="0"> 女
						</td>
					</tr>
				
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="提交注册" /></label></td>
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
