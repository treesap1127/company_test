<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include>
<script src="../js/signup.js"></script>
</head>
<body>
      <div class="center">
       <h1>회원가입</h1>
       <form id="signup_form" method="post">
         <input type="hidden" name="checkId">
         <div class="txt_field">								
           <label>아이디:</label>		
           <input type="text" name="id" id="id" required >							
           <span><button type="button" class="btn btn-warning" value="<c:out value=""/>" onclick="check_id_Async()">중복확인</button></span>			
         </div>
         
         <div class="txt_field">
           <label>사용자 이름:</label>
           <input type="text" name="name" id="name" value="<c:out value=""/>" required >
           <span></span>
         </div>		
         <div class="txt_field">				
           <label>전화번호:</label>
           <input type="tel" name="tel" id="tel" value="<c:out value=""/>" required>
           <h6>(전화번호를 010-1234-1234 형식으로 입력해주세요)</h6>
         </div>
         <input id="check" hidden="true">
         <button type="button" id="signup" onclick="javascript:test()">회원가입</button>	
         <a href="/member/login" class="btn btn-primary" style="display: table-cell;">로그인 하러 가기</a>
       </form>
     
 </div>
    <jsp:include page="../include/body.jsp"></jsp:include>
</body>
</html>