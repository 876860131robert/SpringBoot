<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>Bootstrap 模板555</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- 引入 Bootstrap -->
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
      <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
      <style>  
			body{  
			margin-left:auto;  
			margin-right:auto; 
			margin-TOP:100PX; 
			width:20em;  
			}
      </style>
      
   </head>
   <body>
<!--下面是用户名输入框-->
		<div class="input-group">
		  <span class="input-group-addon" id="basic-addon1">@</span>
		  <input id="userName" type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
		</div>
        <br>
<!--下面是密码输入框-->
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1">@</span>
  <input id="passWord" type="text" class="form-control" placeholder="密码" aria-describedby="basic-addon1">
</div>
<br>
<!--下面是登陆按钮,包括颜色控制-->
<button type="button" style="width:280px;" class="btn btn-default" onclick="submitOk()">登 录</button>
 
      <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
      <script src="https://code.jquery.com/jquery.js"></script>
      <!-- 包括所有已编译的插件 -->
      <script src="js/bootstrap/bootstrap.min.js"></script>
      <script src="business/login/login.js"></script>
   </body>
</html>
