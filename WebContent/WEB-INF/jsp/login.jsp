<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城 - 首页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/font/iconfont.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/function.js"></script>
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
			<h1>欢迎回到商城</h1>
			<form id="loginForm" method="post" action="${pageContext.request.contextPath}/user" onsubmit="return checkForm(this)">
				<input type="hidden" name="method" value="login" />
				<input type="hidden" name="url" value="${url }"/>
				<table>
				   <c:if test="${not empty msg }">
				   		<tr>
				   			<td colspan="2" style="text-align: center;color:red;">
				   				${msg }
				   			</td>
				   		</tr>
				   </c:if>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" id="userName" type="text" name="name" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="pwd" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="CheckItem(this);" />
						<img id="veryCode" src="${pageContext.request.contextPath}/codeImage" /><span></span>
						<a href="javascript:changePic()">看不清，换一张</a>
					</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="checkbox" id="ck" name="remeberMe" value="remeberMe">记住用户
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td><label class="ui-green"><input type="submit" name="submit" value="立即登录" /></label></td>
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
<script type="text/javascript">
	var _v = "${cookie.USERCOOKIE.value}" ;
	if(_v != null && _v.length >0) { 
		
		document.getElementById("userName").value = decodeURI(_v) ;
		document.getElementById("ck").checked = true;
	}
	function changePic(){
		// Math.random()
		document.getElementById("veryCode").src="${pageContext.request.contextPath}/codeImage?code="+new Date().getTime();
	}
</script>
</body>
</html>
