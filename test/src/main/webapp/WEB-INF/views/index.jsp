<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/header.jsp"></jsp:include> 
<meta charset="UTF-8">

</head>
<body>

	<c:import url="/top_menu"></c:import>

<div style="text-align: center;">
 <h1>기초 CRUD</h1>
 <div><a href="/basic1/list" class="btn btn-primary" style="margin:50px;padding:30px;font-size:20px;">테이블 접속</a></div>
</div>
<jsp:include page="include/body.jsp"></jsp:include>
</body>
</html>