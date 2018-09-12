<%@page import="com.sun.glass.ui.Application"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//int i = 0;
		//if (application.getAttribute("i") != null) {
		//	i = (Integer) application.getAttribute("i");
		//}
		//if(session.isNew()){
		//	i++;
		//}
		//application.setAttribute("i", i);
		
		
		//int num = 0;
		//Set<String> set=new HashSet<>();
		//if (application.getAttribute("set") != null) {
		//	set=(Set<String>)application.getAttribute("set");
		//}
		//if (session.isNew()) {
		//	String ip = request.getRemoteAddr();
		//	set.add(ip);
		//	num = set.size();
		//	application.setAttribute("set", set);
		//} else {
		//	num = set.size();
		//}
		
		
		//request.setAttribute("i", 1);
		//session.setAttribute("i", 10);
		//application.setAttribute("i", 100);
		//request>session>application
	%>
	本网页共有<%=application.getAttribute("num")%>人访问
</body>
</html>