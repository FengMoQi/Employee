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
$()
.ready(
		function() {
			var selectId = -1;
			$("#showAdd").click(function() {
				location.href = "pros?type=showAdd";
			})
			$("#showModify")
					.click(
							function() {
								if (selectId > -1) {
									location.href = "pros?type=showModify&id="
											+ selectId;
								} else {
									alert("请选中一条数据")
								}
							})
			$("#showDelete").click(
					function() {
						if (selectId > -1) {
							location.href = "pros?type=delete&id="
									+ selectId;
						} else {
							alert("请选中一条数据")
						}
					})

			$("tr").click(function() {
				$(this).toggleClass("select");
				//selectId = $(this).children().eq(0).text();
				selectId = $(this).data("id");
			})
		})
</script>
<style type="text/css">
#pro .select {
	background: #337ab7
}

table {
	text-align: center;
}

#pro td {
	width: 200px;
}

#pro input {
	width: 100px;
	text-align: center;
}

#pro select {
	width: 100px;
	height: 25px;
	width: 60px;
}
/*table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}

td {
	text-align: center;
	height: 30px;
	vertical-align: bottom;
	padding: 2px;
}

.table tr:nth-child(even) td {
	background-color: #D4FFFF;
}

.table  tr:nth-child(odd) td {
	background-color: #D4BFFF;
}*/
#main {
	width: 600px;
	margin: 20px auto;
}
</style>
<link href="css/bootstrap.min.css" rel="stylesheet" />


</head>
<body>

	<div id="main">
		<form class="form-horizontal" action="pros" role="form" method="post">
			<input type="hidden" name="type" value="search">
			<div class="form-group">
				<div class="col-sm-4">
					<select name="name" class="form-control">
						<option value="">请选择项目</option>
						<c:forEach items="${list}" var="pro">
							<option <c:if test="${pro.name==c.name}">selected</c:if>
								value="${pro.name}">${pro.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-sm-4">
					<button type="submit" class="btn btn-default">查询</button>
				</div>
			</div>
		</form>


		<table id="pro"
			class="table table-hover table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>id</th>
					<th>项目</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pros}" var="pro">
					<tr data-id="${pro.id}">
						<td name="id">${pro.id}</td>
						<td name="name">${pro.name}</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>

		<input type="hidden" name="type" value="pros">
		<button type="button" id="showAdd" class="btn btn-primary">添加</button>
		<button type="button" id="showModify" class="btn btn-primary">修改</button>
		<button type="button" id="showDelete" class="btn btn-primary">删除</button>
		<nav style="text-align: center">
		<ul id="yema" class="pagination">
			<li><a ${ye1=p.ye} <c:if test="${ye1<=1}">
　　　　${ye1=2};
　</c:if>
				class="yemian" href="pros?type=search&ye=${ye1-1}&name=${c.name}">&laquo;</a></li>


			<c:forEach varStatus="i" begin="${p.start}" end="${p.end}">

				<li><a class="yemian" href="pros?type=search&ye=${i.index}&name=${c.name}">${i.index}</a></li>

			</c:forEach>
			<li><a ${ye2=p.ye}
				<c:if test="${ye2>=p.yes}">
　　　　${ye2=p.yes-1};
　</c:if>
				href="pros?type=search&ye=${ye2+1}&name=${c.name}">&raquo;</a></li>
		</ul>
		</nav>
	</div>
</body>
</html>