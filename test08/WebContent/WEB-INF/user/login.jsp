<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

if(self!=top){
	top.location="user";
}
$().ready(function () {
	$("#image").click(function () {
		$(this).attr("src","user?type=randomImage&"+Math.random())
	})
})



</script>
<style>
#form {
	margin-left: 400px;
	margin-top: 200px;
}

body {
	background-image: url(pic/background.jpg);
	width: 1000px;
	height: 360px;
}

#main {
	width: 760px;
	height: 100%;
}
</style>
<link href="css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<form id="form" action="user" method="post">
			<input type="hidden" name="type" value="doLogin">

			<div>
				<label for="firstname" class="col-sm-2 control-label">用户</label>

				<div class="col-sm-10">
					<input type="text" class="form-control" autocomplete="off"
						id="username" name="text1" placeholder="请输入用户名" value="${name}">
				</div>
			</div>
			<div>
				<label for="lastname" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						name="text2" placeholder="请输入密码">
				</div>
			</div>

			<div>
				<label for="lastname" class="col-sm-2 control-label">验证码</label>
				<div class="col-sm-5">
					<input type="text" class="form-control"  autocomplete="off" name="text3"
						placeholder="请输入验证码">
				</div>
				<img class="col-sm-5" id="image" src="user?type=randomImage" />
			</div>
<br/><br/><br/><br/><br/>

			<div>
				<input type="submit" class="btn btn-primary" id="submit" value="登录">
				</button>
				<a id="registe" href="user?type=showRegiste">注册新用户？</a>
			</div>
		</form>
	</div>

</body>
</html>