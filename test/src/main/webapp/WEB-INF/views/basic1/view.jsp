<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include> 
<meta charset="UTF-8">
</head>
<body style="text-align: center;">
<c:import url="/top_menu"></c:import>
		<c:forEach var="item" items="${item}">
		<div>
			글 번호 ${item.code} 파일명 ${item.filename}
		</div>
        <div class="inner_case">
            <table border="1" class="table_all" style="left: 50%;position: relative;transform: translateX(-50%);width: 1000px;">
                 <thead class="table_head">
                    <tr>
                        <td>파일번호</td>
                        <td>순번</td>
                        <td>이름</td>
                        <td>전화번호</td>
                        <td>주소</td>
                    </tr>
                </thead>
    
                <tbody class="table_body">
                    <c:forEach var="list" items="${item.oneExcel}">
                        <tr>
                            <td>${list.filecode}</td>
                            <td>${list.row}</td>
                            <td>${list.name}</td>
                            <td>${list.tel}</td>
                            <td>${list.address}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="/basic1/excelDownload1/${item.filecode}" class="btn btn-primary">서버에 있는 파일 다운로드</a>
            <a href="/basic1/excelDownload2/${item.filecode}" class="btn btn-primary">데이터베이스 파일 다운로드</a>
        </div>
        </c:forEach>
</body>
</html>