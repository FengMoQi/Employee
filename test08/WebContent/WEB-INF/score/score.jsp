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

						$("#ModifyBatch")
								.click(
										function() {
											var array = new Array;
											$(".updateScore")
													.each(
															function(index,
																	element) {
																var id=$(
																		this).data("id");
																
																var empId = $(
																		this).find("[name=empName2]")
																		.data(
																				"eid");
																var proId = $(
																		this).find("[name=proName2]")
																		.data(
																				"pid");
																var scorea = $(this).find("[name=textScore]").val();
																
																var score = {
																		id:id,
																	emp : {
																		id : empId
																	},
																	pro : {
																		id : proId
																	},
																	score : scorea
																}
																array.push(score);

															})
											var str = JSON.stringify(array);
											str = str.replace(/{/g, "%7b");
											str = str.replace(/}/g, "%7d");
											alert(str);
											window.location.href = "score?type=updateBatch&score="
													+ str;
										})

						$("tr")
								.dblclick(
										function() {
											$(this).addClass("updateScore")
											$(this).unbind("dblclick");
											if ($(this).hasClass("select")) {
												$(this).removeClass("select");
											}
											var score = $(this).find(
													"[name=score2]").text();
											var select = "";
											var sql = score;
											$(this)
													.find("[name=score2]")
													.html(
															"<input type='text' name='textScore' value="+score+">");

											//selectId = $(this).children().eq(0).text();
										})
					})
</script>





<style type="text/css">
#emp .select {
	background: #337ab7
}

table {
	text-align: center;
}

#emp td {
	width: 200px;
}

#emp input {
	width: 100px;
	text-align: center;
}

#emp select {
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
		<form class="form-horizontal" action="score" role="form" method="post">
			<input type="hidden" name="type" value="search">
			<div class="form-group">

				<div class="col-sm-2">
					<input type="text" class="form-control" name="name"
						value="${c.emp.name}" placeholder="请输入名字">
				</div>
				<div class="col-sm-2">
					<select name="depName" class="form-control">
						<option value="">请选择部门</option>
						<c:forEach items="${deps}" var="dep">
							<option <c:if test="${dep.name==c.dep.name}">selected</c:if>
								value="${dep.name}">${dep.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="proName"
						value="${c.pro.name}" placeholder="请输入项目">
				</div>

				<div class="col-sm-2">
					<input type="text" class="form-control" name="score"
						value="${c.score}" placeholder="请输入分数">
				</div>
				<div class="col-sm-2">
					<select name="grade" class="form-control">
						<option value="">请选择等级</option>
						<option value="优秀">优秀</option>
						<option value="良好">良好</option>
						<option value="一般">一般</option>
						<option value="及格">及格</option>
						<option value="不及格">不及格</option>
					</select>
				</div>

				<div class="col-sm-2">
					<button type="submit" class="btn btn-default">查询</button>
				</div>
			</div>
		</form>


		<table id="score"
			class="table table-hover table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>姓名</th>
					<th>部门</th>
					<th>项目</th>
					<th>分数</th>
					<th>等级</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${scores}" var="sc">
					<tr data-id="${sc.id}">
						<td  data-eid="${sc.emp.id}"
							name="empName2">${sc.emp.name}</td>
						<td  name="depName2">${sc.dep.name}</td>
						<td  data-pid="${sc.pro.id}" name="proName2">${sc.pro.name}</td>
						<td  name="score2">${sc.score}</td>
						<td  name="grade2">${sc.grade}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<input type="hidden" name="type" value="score">
		<button type="button" id="ModifyBatch" class="btn btn-primary">修改</button>
		<nav style="text-align: center">
		<ul id="yema" class="pagination">
			<li><a class="yemian"
				href="score?type=search&ye=1&name=${c.emp.name}&depName=${c.dep.name}&proName=${c.pro.name}&score=${c.score}&grade=${c.grade}">首页</a></li>
			<li><a ${ye1=p.ye}
				<c:if test="${ye1<=1}">
　　　　${ye1=2};
　</c:if> class="yemian"
				href="score?type=search&ye=${ye1-1}&name=${c.emp.name}&depName=${c.dep.name}&proName=${c.pro.name}&score=${c.score}&grade=${c.grade}">&laquo;</a></li>


			<c:forEach varStatus="i" begin="${p.start}" end="${p.end}">

				<li><a class="yemian"
					href="score?type=search&ye=${i.index}&name=${c.emp.name}&depName=${c.dep.name}&proName=${c.pro.name}&score=${c.score}&grade=${c.grade}">${i.index}</a></li>

			</c:forEach>
			<li><a ${ye2=p.ye}
				<c:if  test="${ye2>=p.yes}">
　　　　${ye2=p.yes-1};
　</c:if>
				href="score?type=search&ye=${ye2+1}&name=${c.emp.name}&depName=${c.dep.name}&proName=${c.pro.name}&score=${c.score}&grade=${c.grade}">&raquo;</a></li>
			<li><a
				href="score?type=search&ye=${p.yes}&name=${c.emp.name}&depName=${c.dep.name}&proName=${c.pro.name}&score=${c.score}&grade=${c.grade}">最后</a></li>
		</ul>
		</nav>
	</div>
</body>
</html>