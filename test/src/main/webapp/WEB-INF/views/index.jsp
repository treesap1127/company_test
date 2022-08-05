<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/header.jsp"></jsp:include> 
<meta charset="UTF-8">

</head>
<body>

	<c:import url="/top_menu"></c:import>

<div style="text-align: center;">
 <h1>기초 CRUD</h1>
 <div><a href="/basic1/list" class="btn btn-primary" style="margin:50px;padding:30px;font-size:20px;">테이블 접속</a></div>
</div>

<form name="calendarFrm" id="calendarFrm" method="GET">
<div class="calendar" >
	<!--날짜 네비게이션  -->
	<div class="navigation">
		<a class="before_after_year" href="/${pageContext.request.contextPath}?year=${today_info.search_year-1}&month=${today_info.search_month-1}">
			&lt;&lt;
		<!-- 이전해 -->
		</a> 
		<a class="before_after_month" href="/${pageContext.request.contextPath}?year=${today_info.before_year}&month=${today_info.before_month}">
			&lt;
		<!-- 이전달 -->
		</a> 
		<span class="this_month">
			&nbsp;${today_info.search_year}. 
			<c:if test="${today_info.search_month<10}">0</c:if>${today_info.search_month}
		</span>
		<a class="before_after_month" href="/${pageContext.request.contextPath}?year=${today_info.after_year}&month=${today_info.after_month}">
		<!-- 다음달 -->
			&gt;
		</a> 
		<a class="before_after_year" href="/${pageContext.request.contextPath}?year=${today_info.search_year+1}&month=${today_info.search_month-1}">
			<!-- 다음해 -->
			&gt;&gt;
		</a>
	</div>

<!-- <div class="today_button_div"> -->
<!-- <input type="button" class="today_button" onclick="javascript:location.href='/calendar.do'" value="go today"/> -->
<!-- </div> -->
<table class="calendar_body">

<thead>
	<tr bgcolor="#CECECE">
		<td class="day" >일</td>
		<td class="day" >월</td>
		<td class="day" >화</td>
		<td class="day" >수</td>
		<td class="day" >목</td>
		<td class="day" >금</td>
		<td class="day" >토</td>
	</tr>
</thead>
<tbody>
	<tr>
		<c:forEach var="dateList" items="${dateList}" varStatus="date_status"> <!-- 상태용 변수! 증가값 확인!!! -->
			<c:choose>
				<c:when test="${dateList.value=='today'}">
					<td class="today" style="background-color:#c9c9c9;">
						<div class="date">
							${dateList.date}
						</div>
						<div>
						</div>
					</td>
				</c:when>
				<c:when test="${dateList.value=='dayoff'&& date_status.index%7==0}">
				</tr>
					<tr>	
					<td class="dayoff" style="color:red">
						<div class="date">
							${dateList.date}
						</div>
						<div>
						</div>
					</td>
				</c:when>
				<c:when test="${dateList.value=='dayoff'}">
					<td class="dayoff" style="color:red">
						<div class="date">
							${dateList.date}
						</div>
						<div>
						</div>
					</td>
				</c:when>
				<c:when test="${date_status.index%7==6}">
					<td class="sat_day" style="color:blue">
						<div class="sat">
							${dateList.date}
						</div>
						<div>
						</div>
					</td>
				</c:when>
			   <c:when test="${date_status.index%7==0}">
					</tr>
					<tr>	
						<td class="sun_day" style="color:red">
							<div class="sun">
								${dateList.date}
							</div>
							<div>
							</div>
						</td>
				</c:when>
				<c:otherwise>
							<td class="normal_day">
								<div class="date">${dateList.date}</div>
								<div></div>
							</td>
				</c:otherwise>
			</c:choose>
		</c:forEach>
</tbody>
</table>
</div>
</form>
	<div>
		<a href="/test" class="btn btn-primary" style="left: 50%;transform: translateX(-50%);position: relative;">테스트 장소 이동</a>
	</div>
<jsp:include page="include/body.jsp"></jsp:include>
</body>
</html>