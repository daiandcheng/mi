<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="top">
	<div class="top_menu wrap">
		<ul class="top_menu_left">
			<li><a href="#"><span class="iconfont icon_log">&#xe67d;</span>湖南</a></li>
		</ul>
		<ul class="top_menu_right">
		  
			<li>
			    <c:if test="${empty USER }">
					<a href="${pageContext.request.contextPath}/user?method=loginUI">你好，请登录</a>
					&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/user?method=registerUI">免费注册</a>
				</c:if>
				<c:if test="${not empty USER }">
					欢迎${USER.name }来到本商城
					&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/user?method=loginOut">注销</a>
				</c:if>
			</li>
			<li class="spacer"></li>
			<c:if test="${not empty USER }">
				<li>
					<a href="${pageContext.request.contextPath}/order?method=list&uid=${USER.id}">我的订单</a>
				</li>
			</c:if>
			<li class="spacer"></li>
			<li>
				<a href="#">企业采购</a>
			</li>
			<li>
				<a href="#">商城会员</a>
			</li>
			<li class="spacer"></li>
			<li>
				<a href="#">客户服务</a>
			</li>
			<li class="spacer"></li>
			<li>
				<a href="#">留言</a>
			</li>
		</ul>
	</div>
</div>
<div id="header" class="nav">
	<div class="wrap">
		<div class="nav_log">
			<a href="#">
				我的商城
			</a>
		</div>
		<div class="div_search">
			<input type="text" placeholder="请输入值" class="sta_search">
			<span class="iconfont camera">&#xe66c;</span>
			<button>
				<span class="iconfont icon_search">&#xe6af;</span>
			</button>
		</div>
		<div class="cart">
			<a href="${pageContext.request.contextPath}/cart?method=list"><span class="iconfont">&#xe750;</span>我的购物车</a>
		</div>
		<div class="nav_title">
			<ul>
				<li><a href="">家用电器</a></li>
				<li><a href="">电脑数码</a></li>
				<li><a href="">男装</a></li>
				<li><a href="">女装</a></li>
				<li><a href="">奶粉</a></li>
				<li><a href="">Mac</a></li>
				<li><a href="">家具</a></li>
				<li><a href="">38妇女节</a></li>
			</ul>
		</div>
		<div class="nav_items">
			<ul>
				<li class="nav_p"><a href="">秒杀</a></li>
				<li class="nav_p"><a href="">优惠券</a></li>
				<li><a href="">PLUS会员</a></li>
				<li><a href="">品牌闪购</a></li>
				<li><a href="">拍卖</a></li>
				<li><a href="">家电</a></li>
				<li><a href="">超市</a></li>
				<li><a href="">男士钱包</a></li>
				<li><a href="">牛肉</a></li>
				<li><a href="">图书</a></li>
				<li><a href="">酒店</a></li>
			</ul>
		</div>
	</div>
</div>