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
<script src="/js/add.js"></script>
<script>
$(function(){
	fileerror=`${fileError}`;
	if(fileerror){
		alert("엑셀파일 및 값을 등록해주세요");
	}
})

</script>
</head>
<body style="text-align: center;">
<c:import url="/top_menu"></c:import>
	  <div class="inner_case" style="padding-bottom: 10rem;">
		<form:form method="post" enctype="multipart/form-data">
			<div class="form-floating">
				<div class="formName">제목</div>
				<input type="text" name="name" value="<c:out value=""/>" class="form-control" > 
				<spring:hasBindErrors name="one">
					<c:if test="${errors.hasFieldErrors('name') }">
					${errors.getFieldError('name').defaultMessage}<br />
					</c:if>
				</spring:hasBindErrors>
			</div>

			<div class="add_info">
				<label>내용</label>
				<input type="text" name="info" value="<c:out value=""/>" class="form-control">
				<spring:hasBindErrors name="one">
					<c:if test="${errors.hasFieldErrors('info') }">
					${errors.getFieldError('info').defaultMessage}<br/>
					</c:if>
				</spring:hasBindErrors>
			</div>
			<div class="add">
 			<button type="button"class="addfile">추가</button> 
			<input type="file" name="files" >
			</div>
			<div class="add_button">
				<button class="end_button">등록</button>
				<a href="/basic1/list" class="back_button">뒤로가기</a>
			</div>
		</form:form>
	</div>
</body>
</html>