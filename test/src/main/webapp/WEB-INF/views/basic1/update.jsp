<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<script src="/js/update.js"></script>
</head>
<body style="text-align: center;">
<c:import url="/top_menu"></c:import>
	  <div class="inner_case" style="padding-bottom: 10rem;">
		<form:form method="post" id="update_form" enctype="multipart/form-data">
			<div class="form-floating">
				<div class="formName">제목</div>
				<input type="text" name="name" id="name" value="<c:out value="${item.name}"/>" class="form-control" > 
			</div>

			<div class="add_info">
				<label>내용</label>
				<input type="text" name="info" id="info" value="<c:out value="${item.info}"/>" class="form-control">
			</div>
 			
 			<c:forEach items="${fileitem}" var="file" >
 			<div style="display:flex; justify-content: center;">
 				<div class="upfile">${file.filename}</div>
 				<button type="button"  id="file_${file.filecode}" value="${file.filecode}" class="btn btn-danger deletefile" >파일 삭제</button>
 									<!--위 파일 삭제시 파일이 하나도 없으면 안됨!-->
 									<!--onefile 은 update excel은 추가-->
 			</div>
 			</c:forEach>
 			<button type="button"class="addfile">추가</button> 
			<input type="file" name="files">
			<div class="add">
			<!-- 삭제 기능과 입력을 하면 추가로? -->
			</div>
			<div class="add_button">
				<button class="end_button" type="button" onclick="test()">등록</button>
				<a href="/basic1/list" class="back_button">뒤로가기</a>
			</div>
		</form:form>
	</div>
</body>
</html>