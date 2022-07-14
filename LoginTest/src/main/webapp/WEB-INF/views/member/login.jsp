<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include> 
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	var login=`${login}`;
	if (login) {
		alert("아이디 비밀번호가 틀렸습니다.");
	}
</script>
</head>

<body>
    <h1>통합로그인</h1> 		
    
		<form id="login_form" method="post">
		   <div class="txt_field">
		    <label>아이디</label>
          	<input type="text" name="id" value="<c:out value=""/>" required>

        </div>
			  <div class="txt_field">
			  <label>비밀번호</label>
        	  <input type="password" name="passwd" value="<c:out value=""/>" required>
        </div>
        <h6>대소문자 구분이 있습니다.</h6>
	  <div class="pass"></div>
        <button>로그인</button>
      </form>

        <div class="signup_link">
          	<a href="/member/signup"class="btn btn-primary">회원가입</a> <a href="/member/findpasswd" class="btn btn-primary">비밀번호찾기</a>
        </div>
    <jsp:include page="../include/body.jsp"></jsp:include>
</body>
</html>