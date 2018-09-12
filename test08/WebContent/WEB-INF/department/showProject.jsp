<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		var selectId = -1;
		var selectName="";
		$("#Add").click(function () {
			$.ajax({
				url:"deps",
				type:"post",
				data:{
					type:"addProject",
					dId:${dId},
					pId:$("[name=project]").val()
					},
				dataType:"text",
				success:function(data){
					$("#pro").html(data);
					$("[name=project] option:selected").remove();
				}
			})
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
						$("#pro").html(data);
						$("[name=project]").append("<option value='"+selectId+"'>"+selectName+"</option>");
					}
				})
			} else {
				alert("请选中一条数据")
			}
		})
		$(document).on("click","tr",function() {
			$(this).toggleClass("select")
			//selectId = $(this).children().eq(0).text();
			selectId = $(this).data("id");
			selectName=$(this).text();
		})
	})
</script>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<style>
#main {
	width: 400px;
	margin: 20px auto;
}

#dep .select {
	background: #337ab7
}

table {
	text-align: center;
}

#dep td {
	width: 200px;
}

#dep input {
	width: 100px;
	text-align: center;
}

#dep select {
	width: 100px;
	height: 25px;
}
</style>
</head>
<body>
	<div id="main">
		<table id="dep"
			class="table table-hover table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>项目</th>
				</tr>
			</thead>
			<tbody id="pro">
				<c:forEach items="${pros}" var="pro">
					<tr data-id="${pro.id}">
						<td name="name">${pro.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="form-group">
			<div class="col-sm-6">
				<select name="project" class="form-control">
					<c:forEach items="${list}" var="proj">
						<option value="${proj.id}">${proj.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-3">
				<button type="button" id="Add" class="btn btn-primary">添加</button>
			</div>

			<div class="col-sm-3">
				<button type="button" id="Delete" class="btn btn-primary">删除</button>
			</div>

		</div>
	</div>
</body>
</html>