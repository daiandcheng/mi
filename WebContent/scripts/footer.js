$(function(){
	// 促销
	$.ajax({
	   type: "GET",
	   url: getRealPath()+"/product?method=pro",
	   success: function(data){
		   $("#pro").empty();
	      console.log(data);
	      data.forEach(function(item,index) {
	    	  var _li = $("<li></li>");
	    	  var _dl = $("<dl></dl>");
	    	  var _dt = $("<dt><a href='"+getRealPath()+"/product?method=detail&id="+item.id+"'><img src='"+item.pic+"'></a></dt>");
	    	  var _dd = $("<dd class='title'><a href='"+getRealPath()+"/product?method=detail&id="+item.id+"' target='_blank'>"+item.title+"</a></dd>");
	    	  var _dd2 = $("<dd class='price'>￥"+item.price+"</dd>");
	    	  _dl.append(_dt);
	    	  _dl.append(_dd);
	    	  _dl.append(_dd2);
	    	  _li.append(_dl);
	    	  $("#pro").append(_li);
	      });
	   }
	});
	// 热卖
	$.ajax({
	   type: "GET",
	   url: getRealPath()+"/product?method=hot",
	   success: function(data){
		   $("#hot").empty();
	      data.forEach(function(item,index) {
	    	  var _li = $("<li></li>");
	    	  var _dl = $("<dl></dl>");
	    	  var _dt = $("<dt><a href='"+getRealPath()+"/product?method=detail&id="+item.id+"'><img src='"+item.pic+"'></a></dt>");
	    	  var _dd = $("<dd class='title'><a href='"+getRealPath()+"/product?method=detail&id="+item.id+"' target='_blank'>"+item.title+"</a></dd>");
	    	  var _dd2 = $("<dd class='price'>￥"+item.price+"</dd>");
	    	  _dl.append(_dt);
	    	  _dl.append(_dd);
	    	  _dl.append(_dd2);
	    	  _li.append(_dl);
	    	  $("#hot").append(_li);
	      });
	   }
	});
});