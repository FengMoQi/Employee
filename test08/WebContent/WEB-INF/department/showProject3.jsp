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
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript">
$().ready(function() {
	var selectId = "";
	var selectName="";
	$("#Add").click(function () {
		if($("#b").find(".select").length>0){
			$("#b").find(".select").each(function () {
				var h=$(this).data("id");
				selectId+=h+",";
			})
			selectId=selectId.substring(0,selectId.length-1);
		$.ajax({
		url:"deps",
		type:"post",
		data:{ 
			type:"addProject3",
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
		if($("#a").find(".select").length>0){
			$("#a").find(".select").each(function () {
				var h=$(this).data("id");
				selectId+=h+",";
			})
			selectId=selectId.substring(0,selectId.length-1);
			$.ajax({
				url:"deps",
				type:"post",
				data:{
					type:"deleteProject3",
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
	})
	
	
	
		var proLeft=$("#yi").offset().left;
		var proTop=$("#yi").offset().top;
		var proWidth=parseFloat($("#yi").css("width"));
		var proHeight=parseFloat($("#yi").css("height"));
		var startLeft;
		var startTop;
	$("#b .tuodong").draggable({
		start:function(){
			startLeft=$(this).offset().left;
			startTop=$(this).offset().top;
		},
		stop:function(){
		var stopLeft=$(this).offset().left;
		var stopTop=$(this).offset().top;
		var sd=$(this);
		if(stopLeft<=proLeft+proWidth&&stopLeft>=proLeft&&stopTop<=proTop+proHeight&&stopTop>=proTop){
			var selectId=$(this).data("id");
			var sd=$(this)
			$.ajax({
				url:"deps",
				type:"post",
				data:{ 
					type:"addProject",
					dId:${dId},
					pId:selectId
					},
				dataType:"text",
				success:function(){
					sd.css("position","static");
					$("#a").append(sd);
					sd.css("position","relative");
					sd.css("left","0");
					sd.css("top","0");
				}	
				})
		}else{
			$(this).offset({left:startLeft,top:startTop});
		}
		}
	});
	
	
	
	
	var proLeft2=$("#er").offset().left;
	var proTop2=$("#er").offset().top;
	var proWidth2=parseFloat($("#er").css("width"));
	var proHeight2=parseFloat($("#er").css("height"));
	var startLeft2;
	var startTop2;
$("#a .tuodong").draggable({
	start:function(){
		startLeft2=$(this).offset().left;
		startTop2=$(this).offset().top;
	},
	stop:function(){
	var stopLeft2=$(this).offset().left;
	var stopTop2=$(this).offset().top;
	var sd=$(this);
	if(stopLeft2<=proLeft2+proWidth2&&stopLeft2>=proLeft2&&stopTop2<=proTop2+proHeight2&&stopTop2>=proTop2){
		var selectId=$(this).data("id");
		var sd=$(this)
		$.ajax({
			url:"deps",
			type:"post",
			data:{ 
				type:"deleteProject",
				dId:${dId},
				pId:selectId
				},
			dataType:"text",
			success:function(){
				sd.css("position","static");
				$("#b").append(sd);
				sd.css("position","relative");
				sd.css("left","0");
				sd.css("top","0");
			}	
			})
	}else{
		$(this).offset({left:startLeft2,top:startTop2});
	}
	}
});
	
	
	
	
	
	
	
	
	
	
	
	
})
</script>
<style type="text/css">
#yi {
	margin-left: 120px;
	width: 600px;
	border: 1px black solid;
	height: 200px;
}

#er {
	margin-left: 120px;
	width: 600px;
	border: 1px black solid;
	height: 200px;
}

#anniu {
	margin-left: 400px;
}

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
	list-style: none;
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

.tuodong {
	margin-left: 5px;
	margin-top: 6px;
	margin-right: 5px;
	margin-bottom: 6px;
	margin-left: 5px;
}
</style>
</head>
<body>
	<div class="btn-group-vertical">
		<div>
			<a id="main">${depName.name}</a>
		</div>
		<div id="yi">
			<ul id="a" class="list-unstyled btn-group">
				<c:forEach items="${pros}" var="pro">
					<li class="tuodong" data-id="${pro.id}"><a>${pro.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="anniu" class="btn-group">
			<div class="col-sm-3">
				<button type="button" id="Add" class="btn btn-primary">↑</button>
			</div>
			<div class="col-sm-3">
				<button type="button" id="Delete" class="btn btn-primary">↓</button>
			</div>
		</div>
		<div id="er">
			<ul id="b" class="list-unstyled btn-group">
				<c:forEach items="${list}" var="proj">
					<li class="tuodong" data-id="${proj.id}"><a>${proj.name}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>

</body>
</html>