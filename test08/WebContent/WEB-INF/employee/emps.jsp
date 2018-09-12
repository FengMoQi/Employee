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
							location.href = "emps?type=showAdd";
						})
						$("#showModify")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "emps?type=showModify&id="
														+ selectId;
											} else {
												alert("请选中一条数据")
											}
										})

						$("#showDelete").click(
								function() {
									if (selectId > -1) {
										location.href = "emps?type=delete&id="
												+ selectId;
									} else {
										alert("请选中一条数据")
									}
								})

						function doBatch(type) {
							var length = $("#emp .select").length;
							var ids = "";
							if (length > 0) {
								$("#emp .select").each(
										function(index, element) {
											ids += $(this).data("id") + ",";
										})
								ids = ids.substring(0, ids.length - 1);
								location.href = "emps?type=" + type + "&ids="
										+ ids;
							} else {
								alert("请选择数据");
							}
						}

						$("#showDeleteBatch").click(function() {
							doBatch("deleteBatch");

						})

						$("#showModifyBatch1").click(function() {

							doBatch("showModifyBatch1");

						})
						$("#showModifyBatch2").click(function() {

							doBatch("showModifyBatch2");

						})
						$("#ModifyBatch3")
								.click(
										function() {
											var array = new Array;
											$(".updateEmp")
													.each(
															function(index,
																	element) {
																var id = $(this)
																		.data(
																				"id");
																var name = $(
																		this)
																		.find(
																				"[name=textName]")
																		.val();
																var sex = $(
																		this)
																		.find(
																				"[name=textSex]")
																		.val();
																var age = $(
																		this)
																		.find(
																				"[name=textAge]")
																		.val();
																var emp = {
																	id : id,
																	name : name,
																	sex : sex,
																	age : age
																}
																array.push(emp);

															})
											var str = JSON.stringify(array);
											str = str.replace(/{/g, "%7b");
											str = str.replace(/}/g, "%7d");

											window.location.href = "emps?type=updateBatch3&emps="
													+ str;
										})

						$("tr").click(function() {
							$(this).toggleClass("select")
							//selectId = $(this).children().eq(0).text();
							selectId = $(this).data("id");
						})

						$("tr")
								.dblclick(
										function() {
											$(this).addClass("updateEmp")
											$(this).unbind("dblclick");
											if ($(this).hasClass("select")) {
												$(this).removeClass("select");
											}
											$(this).unbind("click");

											var name = $(this).find(
													"[name=name]").text();
											var sex = $(this)
													.find("[name=sex]").text();
											var age = $(this)
													.find("[name=age]").text();
											var select = "";
											if (sex == "男") {
												select = "<select name='textSex'><option selected value='男'>男</option><option value='女'>女</option></select>";
											} else {
												select = "<select name='textSex'><option value='男'>男</option><option selected value='女'>女</option></select>"
											}

											var sql = (name + "," + sex + ","
													+ age + ";");
											$(this)
													.find("[name=name]")
													.html(
															"<input type='text' name='textName' value='"+name+"'>");
											$(this).find("[name=sex]").html(
													select);
											$(this)
													.find("[name=age]")
													.html(
															"<input type='text' name='textAge' value='"+age+"'>");

											//selectId = $(this).children().eq(0).text();
										})

						$(".pic").hide();
						$("img").hover(function(even) {
							$(".pic").show();
							$(".pic").css("top", even.pageY);
							$(".pic").css("left", even.pageX);
							var str=$(this).attr("src");
							$(".pic").html("<img class='pi' src="+str+">");
						}, function() {
							$(".pic").hide();
						});

					})
</script>
<style type="text/css">
#emp .select {
	background: #337ab7
}

.pic {
	position: absolute;
}
.pi {
	width: 100px;
	height: 150px;
}
.picture {
	width: 24px;
	height: 30px
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
		<form class="form-horizontal" action="emps" role="form" method="post">
			<input type="hidden" name="type" value="search">
			<div class="form-group">

				<div class="col-sm-3">
					<input type="text" class="form-control" name="name"
						value="${c.name}" placeholder="请输入名字">
				</div>
				<div class="col-sm-2">

					<select name="sex" class="form-control">
						<option value="">请选择性别</option>
						<option value="男" <c:if test="${c.sex == '男'}">selected</c:if>>男</option>
						<option value="女" <c:if test="${c.sex == '女'}">selected</c:if>>女</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="age"
						value="${c.age!=-1?c.age:''}" placeholder="请输入年龄">
				</div>

				<div class="col-sm-3">
					<select name="depName" class="form-control">
						<option value="">请选择部门</option>
						<c:forEach items="${deps}" var="dep">
							<option <c:if test="${dep.name==c.dep.name}">selected</c:if>
								value="${dep.name}">${dep.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<button type="submit" class="btn btn-default">查询</button>
				</div>
			</div>
		</form>


		<table id="emp"
			class="table table-hover table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>id</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>部门</th>
					<th>照片</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${emps}" var="emp">
					<tr data-id="${emp.id}">
						<td name="id">${emp.id}</td>
						<td name="name">${emp.name}</td>
						<td width="100px" name="sex">${emp.sex}</td>
						<td name="age">${emp.age}</td>
						<td name="depName">${emp.dep.name}</td>
						<td name="picture"><img class="picture"
							src="pic/${emp.picture}"></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<div class="pic"></div>
		<input type="hidden" name="type" value="emps">
		<button type="button" id="showAdd" class="btn btn-primary">添加</button>
		<button type="button" id="showModify" class="btn btn-primary">修改</button>
		<button type="button" id="showDelete" class="btn btn-primary">删除</button>
		<button type="button" id="showModifyBatch1" class="btn btn-primary">批量修改1</button>
		<button type="button" id="showModifyBatch2" class="btn btn-primary">批量修改2</button>
		<button type="button" id="ModifyBatch3" class="btn btn-primary">批量修改3</button>
		<button type="button" id="showDeleteBatch" class="btn btn-primary">批量删除</button>
		<nav style="text-align: center">
		<ul id="yema" class="pagination">
			<li><a class="yemian"
				href="emps?type=search&ye=1&name=${c.name}&sex=${c.sex}&age=${c.age}&depName=${c.dep.name}">首页</a></li>
			<li><a ${ye1=p.ye}
				<c:if test="${ye1<=1}">
　　　　${ye1=2};
　</c:if> class="yemian"
				href="emps?type=search&ye=${ye1-1}&name=${c.name}&sex=${c.sex}&age=${c.age}&depName=${c.dep.name}">&laquo;</a></li>


			<c:forEach varStatus="i" begin="${p.start}" end="${p.end}">

				<li><a class="yemian"
					href="emps?type=search&ye=${i.index}&name=${c.name}&sex=${c.sex}&age=${c.age}&depName=${c.dep.name}">${i.index}</a></li>

			</c:forEach>
			<li><a ${ye2=p.ye}
				<c:if  test="${ye2>=p.yes}">
　　　　${ye2=p.yes-1};
　</c:if>
				href="emps?type=search&ye=${ye2+1}&name=${c.name}&sex=${c.sex}&age=${c.age}&depName=${c.dep.name}">&raquo;</a></li>
			<li><a
				href="emps?type=search&ye=${p.yes}&name=${c.name}&sex=${c.sex}&age=${c.age}&depName=${c.dep.name}">最后</a></li>
		</ul>
		</nav>
	</div>
</body>
</html>