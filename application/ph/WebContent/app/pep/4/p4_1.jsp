<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.saturn.tc.utils.DatasetUtils"%>
<%@page import="com.saturn.ph.PH"%>
<%@page import="java.io.File"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<%@ include file="/app/pep/include/header.jsp"%>
	</head>
	
	<%
	String uid = (String)request.getAttribute("uid");
	String src =  DatasetUtils.getDatasetByUid(uid, request);
	%>
	<body>
		<div id="container">
			<div id="nr">
			<div id="top"><h1>4.4 Aggregateverfügbarkeit ZP3/ZP4(图片)</h1></div>
			<div id="content">
				<div id="chart" style="width: 800px; height: 400px; margin: 0 auto">
					<img src = "<%=src%>" >
				</div>
			</div>
			<%@ include file="/app/pep/include/foot.jsp"%>
		</div>	
	</body>
</html>
