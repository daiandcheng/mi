function getRealPath(){
	//获取当前网址，如： http://localhost:8083/myproj/view/ahha.jsp
	var curWwwPath=window.document.location.href;
	//获取主机地址之后的目录，如： myproj/view/ahha.jsp
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8080
	var localhostPaht=curWwwPath.substring(0,pos);
	//获取带"/"的项目名，如：/ahha
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	 
	//得到了 服务器名称和项目名称
	var realPath=localhostPaht+projectName;
	return realPath;
}
$(function(){
    var flag = {'name':false,'pwd':false,'repwd':false,'phone':false,'email':false,'birthday':false};
    // 给用户名绑定一个失去焦点的时间
    $("input[name='name']").blur(function(){
        var _v = $(this).val();
        var _reg = /^([a-z0-9_-]{6,16})|([\u2E80-\u9FFF]{2,6})$/ ;
        if(_reg.test(_v)) {
        	var _this = $(this);
            $(this).next().css("display","none").html("");
//            flag.name = true;
            // 如果数据库中，存在当前输入的用户名，则提示当前用户已经存在，否则可以使用
            // AJAX 技术，异步刷新，或者无刷新  
            // JSON数据格式  {} 对象 [] 数据 msg = {"code":200,"msg":"响应成功",data:{}}
            $.ajax({
        	   type: "GET",
        	   url: getRealPath()+'/user?method=checkUser&name='+_v,
        	   success: function(msg){
        		  if(msg.code == 200) {
        			  _this.next().css("display","none").html("");
        			  flag.name = true;
        		  } else {
        			  _this.next().css("display","block").html(msg.msg);
        			  flag.name = false;
        		  }
        	   }
        	});
        } else {
            $(this).next().css("display","block").html("你输入的用户名格式不正确.....");
            flag.name = false;
        }
    });
     // 给用户名绑定一个失去焦点的时间
     $("input[name='pwd']").blur(function(){
        var _v = $(this).val();
        var _reg = /^[a-z0-9_-]{6,18}$/ ;
        if(_reg.test(_v)) {
            $(this).next().css("display","none").html("");
            flag.pwd = true;
        } else {
            $(this).next().css("display","block").html("密码至少为6到18位字符.....");
            flag.pwd = false;
        }
    });
    // 给用户名绑定一个失去焦点的时间
    $("input[name='rePassWord']").blur(function(){
        var _rv = $(this).val();
       var _v = $("input[name='pwd']").val();
        if(_rv== null || _rv.length == 0) {
            $(this).next().css("display","block").html("确认密码不能为空....");
            flag.repwd = false;
            return ;
        }
        if(_rv==_v) {
            $(this).next().css("display","none").html("");
            flag.repwd = true;
        } else {
            $(this).next().css("display","block").html("两次密码必须一致.....");
            flag.repwd = false;
        }
    });

    $("input[name='phone']").blur(function(){
        var _v = $(this).val();
        var _reg = /^1[345789]\d{9}$/ ;
        if(_reg.test(_v)) {
            $(this).next().css("display","none").html("");
            flag.phone = true;
        } else {
            $(this).next().css("display","block").html("手机必须符合手机格式");
            flag.phone = false;
        }
    });
    $("input[name='email']").blur(function(){
        var _v = $(this).val();
        var _reg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/ ;
        if(_reg.test(_v)) {
            $(this).next().css("display","none").html("");
            flag.email = true;
        } else {
            $(this).next().css("display","block").html("必须符合邮箱格式");
            flag.email = false;
        }
    });
    $("input[name='birthday']").blur(function(){
        var _v = $(this).val();
        var _reg = /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))([ ])([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/;
        if(_reg.test(_v)) {
            $(this).next().css("display","none").html("");
            flag.birthday = true;
        } else {
            $(this).next().css("display","block").html("必须符合生日格式2001-10-12 10:10:10");
            flag.birthday = false;
        }
    });
    $("#regForm").submit(function(){
        $("input[name='name']").blur();
        $("input[name='pwd']").blur();
        $("input[name='rePassWord']").blur();
        $("input[name='phone']").blur();
        $("input[name='email']").blur();
        $("input[name='birthday']").blur();
        return flag.name && flag.pwd && flag.repwd && flag.phone && flag.email && flag.birthday;
    });
});