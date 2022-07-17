<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl id="dl">
				
			</dl>
		</div>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix" id="recent">
				<!-- <dt><img src="images/product/0_tiny.gif" /></dt>
				<dd><a href="product-view.html">法国德菲丝松露精品巧克力500g/盒</a></dd>
				<dt><img src="images/product/0_tiny.gif" /></dt>
				<dd><a href="product-view.html">法国德菲丝松露精品巧克力500g/盒</a></dd> -->
			</dl>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		$.ajax({
		   type: "GET",
		   url: "${pageContext.request.contextPath}/category?method=list",
		   success: function(data){
			   $("#dl").empty();
		   	  data.forEach(function(item,index){
		   		  //console.log(item);
		   		  //<dt>图书音像</dt>
				  // <dd><a href="product-list.html">图书</a></dd>
				  //	<dd><a href="product-list.html">音乐</a></dd>
				  var _dt = $("<dt>"+item.name+"</dt>");
				  $("#dl").append(_dt);
				  item.childrens.forEach(function(ch,ix) {
					  var _dd = $("<dd><a href='#'>"+ch.name+"</a></dd>") ;
					  $("#dl").append(_dd);
				  });
				  
		   	  });
		   }
		});
		// 从cookie中取出数据
		var _data = "${cookie.cookie_item_product.value}" ;
		_data = decodeURIComponent(_data);// 解码之后是一个字符串，并不是一个json数据格式
		console.log(_data);
		// 把字符串转换成json数据格式
		_data = $.parseJSON(_data);
		if(_data != null && _data.length > 0) {
			$("#recent").empty();
			_data.forEach(function(item,index) {
				console.log(item);
				// <dt><img src="images/product/0_tiny.gif" /></dt>
				// <dd><a href="product-view.html">法国德菲丝松露精品巧克力500g/盒</a></dd>
				var _dt = $("<dt><img src="+item.pic+" width='50px' height='50px'/></dt>");
				var _dd = $("<dd><a href='${pageContext.request.contextPath}/product?method=detail&id="+item.id+"'>"+item.title+"</a></dd>");
				$("#recent").append(_dt).append(_dd);
			});
		}
		
	});
</script>