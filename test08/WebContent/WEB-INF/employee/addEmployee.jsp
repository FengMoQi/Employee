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
$().ready(function () {
	$("#upload").click(function(){
		var formData=new FormData();
		for(var i=0;i<$("[name=pic]")[0].files.length;i++){
			formData.append("pic",$("[name=pic]")[0].files[i]);
		}
		$.ajax({
			url:"emps?type=upload",
			type:"post",
			data:formData,
			cache:false,//禁止缓存
			processData:false,//禁止序列化,
			contentType:false,//禁止jquery进行操作导致失去分界符
			dataType:"json",
			success:function(data){
				for(var i=0;i<data.length;i++){
					$("#pics").append("<img src='/pic/"+data[i].picName+"'>");
					$("#f").append("<input type='hidden' name='picName' value='"+data[i].picName+"'>");
				}
			}
		});
	});
})

</script>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<style type="text/css">
#main {
	width: 400px;
	margin: 20px auto;
}
</style>
</head>
<body>
	<div id="main">
		<form class="form-horizontal" action="emps?type=add" role="form" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						placeholder="请输入名字">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" name="sex" checked value="男">男 <input
						type="radio" name="sex" value="女">女
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						placeholder="请输入年龄">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<select name="depID" class="form-control">
						<option value="">请选择部门</option>
						<c:forEach items="${deps}" var="dep">
							<option value="${dep.id}">${dep.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">照片</label>
				<div class="col-sm-6">
				<input type="file" value="选择文件" name="myFile" multiple/>
				</div>
			</div>
div
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">上傳</button>
					<button type="submit" class="btn btn-default">保存</button>
				</div>
			</div>


		</form>
	</div>

</body>
</html>