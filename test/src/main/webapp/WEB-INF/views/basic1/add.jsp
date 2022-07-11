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
<script src="../js/add.js"></script>
</head>
<body style="text-align: center;">
	  <div class="inner_case" style="padding-bottom: 10rem;">
		<form method="post" enctype="multipart/form-data">
			<div class="form-floating">
				<div class="formName">제목</div>
				<input type="text" name="name" class="form-control" > 
			</div>

			<div class="add_info">
				<label>내용</label>
				<input type="text" name="info" class="form-control">
			</div>
			<div class="add" >
			<button type="button"class="addfile">추가</button>
			<input type="file" name="files">
			</div>
			<div class="add_button">
				<button class="end_button">등록</button>
				<a href="list" class="back_button">뒤로가기</a>
			</div>
		</form>
	</div>
</body>
</html>