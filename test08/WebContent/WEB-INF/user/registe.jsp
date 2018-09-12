<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js">
<!--
	
//-->
</script>
<script type="text/javascript">
	$().
	ready(function() {
		$("#btn").click(function() {
			var name=$("#username").val();
			var password=$("#password").val();
			//location.href = "user?type=registe&userName='"+$("[name=userName]").val()+"'&password="+$("[name=password]").val();
			location.href="user?type=registe&userName="+name+"&passwords="+password;
		})
	})
</script>
<style type="text/css">
#main{
width:400px;
margin-left: 320px;
margin-top: 140px;
}
#font{
color:purple;
margin-left:160px;
margin-bottom:20px;
font-size: 24px;
}
#third{
margin-left: 160px;
margin-top: 100px;
}
</style>
</head>
<body>

	<div id="main">
		<div>
			<p id="font">欢迎注册</a>
		</div>
		<div>
		<label for="firstname" class="col-sm-2 control-label">用户</label>
		
		<div class="col-sm-10">
					<input type="text" class="form-control" id="username" name="userName"
						placeholder="请输入用户名">
				</div>
		</div>
		<div>
			<label for="firstname" class="col-sm-2 control-label">密码</label>
			<div class="col-sm-10">
					<input type="password" class="form-control" id="password" name="password"
						placeholder="请输入密码">
				</div>
		</div>
		<div id="third">
			<input type="button" class="btn btn-default" value="注册" id="btn">
		</div>



	</div>




</body>
</html>