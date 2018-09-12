<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function() {
	var selectId = -1;
	var selectName="";
	$("#Add").click(function () {
		if (selectId > -1) {
		$.ajax({
			url:"deps",
			type:"post",
			data:{
				type:"addProject",
				dId:${dId},
				pId:selectId
				},
			dataType:"text",
			success:function(data){
				$("#a").html(data);
				$("#b").children(".select").remove();
			}
		})
	} else {
		alert("请选中一条数据")
	}
	})
	$("#Delete").click(function () {
		if (selectId > -1) {
			$.ajax({
				url:"deps",
				type:"post",
				data:{
					type:"deleteProject",
					dId:${dId},
					pId:selectId
					}, 
				dataType:"text",
				success:function(data){
					$("#b").html(data);
					$("#a").children(".select").remove();
				}
			})
		} else {
			alert("请选中一条数据")
		}
	})
	
	
	$(document).on("click","li",function() {
		$(this).toggleClass("select")
		//selectId = $(this).children().eq(0).text();
		selectId = $(this).data("id");
		selectName=$(this).text();
	})
})
</script>
<style type="text/css">
#main {
	font-size: 40px
}

#a .select {
	background: #337ab7
}

#b .select {
	background: #337ab7
}

#a {
	font-size: 18px;
	font-weight: bold;
}

#a li {
	list-style: none;
	height: 40px;
	padding-top: 5px;
	padding-right: 10px;
	padding-bottom: 5px;
	padding-left: 10px;
	float: left;
	border: 1px solid black;
}

#a li a {
	color: #040404;
	text-decoration: none;
	margin: 0px;
	display: block;
}

#b {
	font-size: 18px;
	font-weight: bold;
}

#b li {
	list-style: none;
	height: 40px;
	padding-top: 5px;
	padding-right: 10px;
	padding-bottom: 5px;
	padding-left: 10px;
	float: left;
	border: 1px solid black;
	padding-top: 5px;
	padding-right: 10px;
	padding-bottom: 5px;
	padding-left: 10px;
}

#b li a {
	color: #040404;
	text-decoration: none;
	margin: 0px;
	display: block;
}
</style>
</head>
<body>
	<div class="btn-group-vertical">
		<div>
			<a id="main">${depName.name}</a>
		</div>
		<div>
			<ul id="a" class="list-unstyled btn-group">
				<c:forEach items="${pros}" var="pro">
					<li data-id="${pro.id}"><a>${pro.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="btn-group">
			<div class="col-sm-3">
				<button type="button" id="Add" class="btn btn-primary">↑</button>
			</div>
			<div class="col-sm-3">
				<button type="button" id="Delete" class="btn btn-primary">↓</button>
			</div>
		</div>
		<div>
			<ul id="b" class="list-unstyled btn-group">
				<c:forEach items="${list}" var="proj">
					<li data-id="${proj.id}"><a>${proj.name}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>

</body>
</html>