<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/header.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	var pass=`${passwd}`;
	var update=`${update}`;
	if (pass!=0) {
		alert("로그인을 해주세요\n임시 비밀번호:"+`${passwd}`);
	}
	if(update==1){
		alert("시간이 오래 지나 세션이 만료되었습니다 \n 다시 로그인해주세요");
	}
</script>
</head>
<body>
	<a href="/member/signup" style="font-size:40px;"class="btn btn-primary">회원가입</a>
	<c:if test="${empty sessionScope.member.id}">   <a href="/member/login" style="font-size:40px;"class="btn btn-primary">로그인</a></c:if>
    <c:if test="${not empty sessionScope.member.id}">   <a href="/member/logout" style="font-size:40px;"class="btn btn-primary">로그아웃</a></c:if>
	<a href="/member/findpasswd" style="font-size:40px;"class="btn btn-primary">비밀번호찾기</a>
	
	<c:if test="${not empty sessionScope.member.id}">
	   <div>${sessionScope.member.id} 사용자님 안녕하세요</div>
	</c:if>
	
	
<jsp:include page="include/body.jsp"></jsp:include>
</body>
</html>