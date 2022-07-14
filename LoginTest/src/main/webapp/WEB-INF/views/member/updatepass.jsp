<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include> 
<script>
$(function(){
	alert("첫 로그인 시 비밀번호를 바꿔주셔야합니다.");
})
</script>
</head>

<body>
    <h1>비밀번호 변경</h1> 		
    
		<form id="login_form" method="post">
		   <div class="txt_field">
		    <label>아이디</label>
          	<input type="text" name="id" value="<c:out value="${id}" />" readonly>
        </div>
			  <div class="txt_field">
			  <label>비밀번호</label>
        	  <input type="password" name="passwd" value="<c:out value=""/>" required>
        </div>
        <button>비밀번호 변경</button>
      </form>

    <jsp:include page="../include/body.jsp"></jsp:include>
</body>
</html>