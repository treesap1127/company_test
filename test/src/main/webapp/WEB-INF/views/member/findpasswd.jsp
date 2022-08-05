<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include> 
<script>
var pass=`${passwd}`;
if (pass==1) {
	alert("존재하는 아이디가 없습니다.");
}
</script>
<style>
	.txt_field{	text-align: left;}
</style>
</head>

<body style="text-align: center; width: 20%; position: relative; left: 50%; transform: translateX(-50%);">
    <h1>비밀번호 찾기</h1> 		
		<form id="login_form" method="post">
		   <div class="txt_field" style="margin-left: 30px; margin-top: 50px;">
		    	<label>아이디</label>
          		<input type="text" name="id" value="<c:out value=""/>" required>
           </div>
        <button style="margin-top: 30px;">비밀번호 찾기</button>
      </form>

        <div class="signup_link" style="margin-top:100px;">
          	<a href="/member/signup"class="btn btn-primary" style="margin: 30px">회원가입</a> <a href="/member/login" class="btn btn-primary" style="margin: 30px">로그인</a><a href="/" style="margin: 30px" class="btn btn-warning">첫 화면으로 나가기</a>
        </div>
    <jsp:include page="../include/body.jsp"></jsp:include>
</body>
</html>