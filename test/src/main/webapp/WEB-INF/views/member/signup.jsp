<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include>
<script src="../js/signup.js"></script>
</head>
<body>
	<div class="center">
		<h1>회원가입</h1>
		<form:form id="signup_form" method="post">
			<input type="hidden" name="checkId" value="<c:out value=""/>">
			<div class="txt_field">
				<label>아이디:</label> <input type="text" name="id" id="id" value="<c:out value=""/>" required>
				<span><button type="button" class="btn btn-warning" onclick="check_id_Async()">중복확인</button></span>
				<input type="radio"id="idchecktrue" name="idcheck" value=true hidden="true">
				<input type="radio"id="idcheckfalse" name="idcheck" value=false hidden="true">
				
				<spring:hasBindErrors name="user">
					<c:if test="${errors.hasFieldErrors('idcheck') }">
					${errors.getFieldError('idcheck').defaultMessage}<br />
					</c:if>
				</spring:hasBindErrors>
			</div>
			<spring:hasBindErrors name="user">
				<c:if test="${errors.hasFieldErrors('id') }">
					${errors.getFieldError('id').defaultMessage}<br />
				</c:if>
			</spring:hasBindErrors>

			<div class="txt_field">
				<label>사용자 이름:</label> <input type="text" name="name" id="name" value="<c:out value=""/>" required>
				<spring:hasBindErrors name="user">
					<c:if test="${errors.hasFieldErrors('name') }">
					${errors.getFieldError('name').defaultMessage}<br />
					</c:if>
				</spring:hasBindErrors>

			</div>
			<div class="txt_field">
				<label>전화번호:</label> <input type="tel" name="tel" id="tel" value="<c:out value=""/>" required>
				<h6>(전화번호를 010-1234-1234 형식으로 입력해주세요)</h6>
				<spring:hasBindErrors name="user">
					<c:if test="${errors.hasFieldErrors('tel') }">
					${errors.getFieldError('tel').defaultMessage}<br />
					</c:if>
				</spring:hasBindErrors>
			</div>
			<div><button id="signup">회원가입</button></div>
			<div>
			<a href="/member/login" class="btn btn-primary" style="margin: 30px">로그인 하러 가기</a>
			<a href="/" class="btn btn-warning" style="margin: 30px">첫 화면으로 나가기</a>
			</div>
		</form:form>

	</div>
	<jsp:include page="../include/body.jsp"></jsp:include>
</body>
</html>