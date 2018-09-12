<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		var selectId = -1;
		$("#showAdd").click(function() {
			location.href = "deps?type=showAdd";
		})

		$("#showModify").click(function() {
			if (selectId > -1) {
				location.href = "deps?type=showModify&id=" + selectId;
			} else {
				alert("请选中一条数据")
			}
		})
		$("#showDelete").click(function() {
			if (selectId > -1) {
				location.href = "deps?type=delete&id=" + selectId;
			} else {
				alert("请选中一条数据")
			}
		})
		$("#showProject").click(function() {
			if (selectId > -1) {
				location.href = "deps?type=showProject&id=" + selectId;
			} else {
				alert("请选中一条数据")
			}
		})

		$("#showProject3").click(function() {
			if (selectId > -1) {
				location.href = "deps?type=showProject3&id=" + selectId;
			} else {
				alert("请选中一条数据")
			}
		})

		$("tr").click(function() {
			$(this).toggleClass("select")
			//selectId = $(this).children().eq(0).text();
			selectId = $(this).data("id");
		})
	})
</script>
<style type="text/css">
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
		<form class="form-horizontal" action="deps" role="form" method="post">
			<input type="hidden" name="type" value="search">
			<div class="form-group">
				<div class="col-sm-3">
					<select name="name" class="form-control">
						<option value="">请选择部门</option>
						<c:forEach items="${list}" var="dep">
							<option <c:if test="${dep.name==c.name}">selected</c:if>
								value="${dep.name}">${dep.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="empCount"
						value="${c.empCount!=-1?c.empCount:''}" placeholder="请输入员工数">
				</div>
				<div class="col-sm-3">
					<button type="submit" class="btn btn-default">查询</button>
				</div>
			</div>
		</form>


		<table id="dep"
			class="table table-hover table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>id</th>
					<th>部门</th>
					<th>员工数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${deps}" var="dep">
					<tr data-id="${dep.id}">
						<td name="id">${dep.id}</td>
						<td name="name">${dep.name}</td>
						<td name="empCount"><a href="emps?depName=${dep.name}">${dep.empCount}</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>

		<input type="hidden" name="type" value="deps">
		<button type="button" id="showAdd" class="btn btn-primary">添加</button>
		<button type="button" id="showModify" class="btn btn-primary">修改</button>
		<button type="button" id="showDelete" class="btn btn-primary">删除</button>
		<button type="button" id="showProject" class="btn btn-primary">项目</button>
		<button type="button" id="showProject3" class="btn btn-primary">项目3</button>




		<button id="motai" class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">开始演示模态框</button>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
					</div>
					<div id="addMotai" class="modal-body">


						<div id="main">
							<form class="form-horizontal" action="deps" role="form"
								method="post">
								<input type="hidden" name="type" value="add">
								<div class="form-group">
									<label for="firstname" class="col-sm-2 control-label">部门</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="name"
											placeholder="请输入部门">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">保存</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>



<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2">
	开始演示模态框
</button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					模态框（Modal）标题
				</h4>
			</div>
			<div class="modal-body" >
				<iframe id="right" name="right" frameborder="0" scrolling="no"
			src="deps"> </iframe>
			</div>
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>





		<nav style="text-align: center">
		<ul id="yema" class="pagination">
			<li><a ${ye1=p.ye}
				<c:if test="${ye1<=1}">
　　　　${ye1=2};
　</c:if> class="yemian"
				href="deps?type=search&ye=${ye1-1}&name=${c.name}&empCount=${c.empCount}">&laquo;</a></li>


			<c:forEach varStatus="i" begin="${p.start}" end="${p.end}">

				<li><a class="yemian"
					href="deps?type=search&ye=${i.index}&name=${c.name}&&empCount=${c.empCount}">${i.index}</a></li>

			</c:forEach>
			<li><a ${ye2=p.ye}
				<c:if test="${ye2>=p.yes}">
　　　　${ye2=p.yes-1};
　</c:if>
				href="deps?type=search&ye=${ye2+1}&name=${c.name}&empCount=${c.empCount}">&raquo;</a></li>
		</ul>
		</nav>
	</div>
</body>
</html>