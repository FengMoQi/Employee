<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	
	
	
var websocket = null;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	websocket = new WebSocket("ws://192.168.0.190:8080/test08/websocket");
} else {
	alert('没有建立websocket连接')
}

//连接发生错误的回调方法
websocket.onerror = function() {
	//setMessage("错误");
};

//连接成功建立的回调方法
websocket.onopen = function(event) {
	//setMessage("建立连接");
}

//接收到消息的回调方法
websocket.onmessage = function(event) {
	//setMessage(event.data);
	$("#count").html(event.data);
}

//连接关闭的回调方法
websocket.onclose = function() {
	//setMessage("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
window.onbeforeunload = function() {
	websocket.close();
}

//将消息显示在网页上
//function setMessage(text) {
//	var message = $("#mes").html();
//	$("#mes").html(message + text + "<br/>")
//}

//关闭连接
function closeWebSocket() {
	websocket.close();
}

//发送消息
//function send() {
//	var message = $("#text").val();
//	websocket.send(message);
//}
	
	
	
	
	
	
	
	$().ready(function() {
		$(".yi").click(function() {
			//if ($(this).next().css("display") == "block") {
			//	$(this).next().css("display", "none");
			//} else {
			//	$(this).next().css("display", "block");
			//}

			$(this).next().slideToggle(500);

		})
	})
</script>
<style type="text/css">
#top a {
	line-height: 100px;
	font-size: 30px;
}

#mian {
	top: 20px;
	overfloat: hidden;
	float: left;
}

#left {
	width: 200px;
	height: 600px;
	float: left;
}

#left a {
	color: #fff;
	text-decoration: none;
}

#right {
	margin-left: 60px; width : 800px;
	height: 600px;
	float: left;
	width: 800px;
}

#top, #botton {
	width: 100%;
	height: 100px;
	text-align: center;
	background-color: #337ab7;
	clear: both;
}

.yi {
	margin-top: 5px;
	margin-left: 30px;
	background-color: #337ab7;
	text-align: center;
	line-height: 36px;
	height: 36px;
	text-align: center;
	background-color: #337ab7;
}

#left .er a {
	background: graytext;
	color: black;
	padding: 6px 30px 6px 30px;
}

li {
	list-style: none;
	margin-top: 5px;
	text-align: center;
	line-height: 36px;
	text-align: center;
	line-height: 36px;
	height: 36px;
}
#top #text{
font-size: 24px;
color: purple;
}

#count{
background-color: red;
width: 60px;
height: 20px;
color:purple;
font-weight: bold;
position:relative;
top:0px;
right: -16px;
}
</style>
</head>
<body>

	<div id="top">
	<a id="text">${user}</a>
		<a>员工信息管理系统</a>
		<a id="count">${applicationScope.num }</a>
	</div>
	<div></div>
	<div id="main">
		<div id="left">
			<div class="yi">
				<a>员工</a>
			</div>
			<ul id="u1" class="er">
				<li calss="menul"><a href="emps" target="right">员工系统</a></li>
				<li calss="menul"><a href="emps?type=showAdd" target="right">员工添加</a></li>
			</ul>
			<div class="yi">
				<a>部门</a>
			</div>
			<ul id="u2" class="er">
				<li calss="menul"><a href="deps" target="right">部门系统</a></li>
				<li calss="menul"><a href="deps?type=showAdd" target="right">部门添加</a></li>
			</ul>
			<div class="yi">
				<a>项目</a>
			</div>
			<ul id="u3" class="er">
				<li calss="menul"><a href="pros" target="right">项目系统</a></li>
				<li calss="menul"><a href="pros?type=showAdd" target="right">项目添加</a></li>
			</ul>
			<div class="yi">
				<a>绩效</a>
			</div>
			<ul id="u4" class="er">
				<li calss="menul"><a href="score" target="right">项目系统</a></li>
				<li calss="menul"><a href="score?type=showAdd" target="right">项目添加</a></li>
			</ul>
		</div>
		<iframe id="right" name="right" frameborder="0" scrolling="no"
			src="emps"> </iframe>
	</div>
	<div id="botton"></div>
	</div>


</body>
</body>
</html>