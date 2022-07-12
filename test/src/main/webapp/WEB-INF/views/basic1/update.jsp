<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include> 
<meta charset="UTF-8">
<style>
.form-control{
	width:200px;
	position: relative;
	left: 50%;
	transform:translateX(-50%);
}
</style>
</head>
<body style="text-align: center;">
	  <div class="inner_case" style="padding-bottom: 10rem;">
		<form method="post">
			<div class="form-floating">
				<div class="formName">제목</div>
				<input type="text" name="name" class="form-control" value="${item.name}" > 
			</div>

			<div class="add_info">
				<label>내용</label>
				<input type="text" name="info" class="form-control" value="${item.info}">
			</div>

			<div class="add_button">
				<button class="end_button">등록</button>
				<a href="../list" class="back_button">뒤로가기</a>
			</div>
		</form>
	</div>
</body>
</html>