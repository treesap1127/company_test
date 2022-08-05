<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include> 
<script>

$(function(){
	alert("비밀번호 변경 후 첫 로그인 시 비밀번호를 바꿔주셔야합니다.");
})
function test() {
	//특수문자,문자,숫자 포함 형태의 8~15자리 비밀번호 식!
	const regx = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=()]).*$/;
	passwd=$("#passwd").val();
	passwdcheck=$("#passwdcheck").val();
	console.log(passwd);
	if (!regx.test($("#passwd").val())) {
		alert("비밀번호 형식을 지켜주세요");
		$("#passwd").focus();
	}
	else if(passwd!=passwdcheck){
		alert("비밀번호와 비밀번호 확인이 다릅니다.");
		$("#passwdcheck").focus();
	}
	else{
		login_form.submit();
	}
}
</script>
<style>
	.txt_field{	text-align: left;}
</style>
</head>

<body style="text-align: center; width: 20%; position: relative; left: 50%; transform: translateX(-50%);">
    <h1>비밀번호 변경</h1> 		
    
		<form name="login_form"id="login_form" method="post">
		   <div class="txt_field">
		    <label>아이디</label>
          	<input type="text" name="id" value="<c:out value="${id}" />" readonly>
        </div>
		<div class="txt_field">
			  <label>비밀번호</label>
        	  <input type="password" name="passwd" id="passwd" value="<c:out value=""/>" required>
        	   <p>(특수문자,문자,숫자 포함 형태의 8~15자리 이내로 쓰시오)</p>
        </div>
        
        <div class="txt_field">
			  <label>비밀번호 확인</label>
        	  <input type="password" name="passwdcheck" id="passwdcheck" value="<c:out value=""/>" required>
        	   <p>(위 비밀번호와 동일하게 작성해주세요)</p>
        </div>
        <button type="button" onclick="test()">비밀번호 변경</button>
      </form>
    <jsp:include page="../include/body.jsp"></jsp:include>
</body>
</html>