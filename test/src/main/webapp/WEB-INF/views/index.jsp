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



<div style="width:1000px;height:1000px;position:relative">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f9054b4984f2033fe99fc7e44f69cdad&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f9054b4984f2033fe99fc7e44f69cdad"></script>
<script>
function addressTest(){
	  new daum.Postcode({
	        oncomplete: function(data) {
	        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                addr = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                //주소 정보를 해당 필드에 넣는다.
                document.getElementById("test").value = addr;
            }
        }).open();
}
function outTest(){
	addr=document.getElementById("test").value;
    //주소 검색 값 좌표로 변환
    let geocoder = new daum.maps.services.Geocoder();
    geocoder.addressSearch(addr, function(result, status) {
    // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {
       	 epy=result[0].y;
       	 epx=result[0].x;
       	} 
	});
if (navigator.userAgent.match(/iPad|Tablet|Android|iPhone|iPod/i)){
        addr=encodeURI(addr);
   		console.log("https://m.map.kakao.com/scheme/route?ep="+epy+"%2C"+epx+"&en=K-water%EC%84%B8%EC%A2%85%EA%B4%80&sp=35.87060079847048%2C127.43702303117742&sn="+addr+"&by=car","_blank");
   		window.open("https://m.map.kakao.com/scheme/route?ep="+epy+"%2C"+epx+"&en=K-water%EC%84%B8%EC%A2%85%EA%B4%80&sp=35.87060079847048%2C127.43702303117742&sn="+addr+"&by=car","_blank");
} else {
        // 기타 PC 브라우저
         window.open("https://naver.com","_blank")
}
}
function outTest2(){
	location.href="https://map.kakao.com/?sName=메이아이&eName=서울시청";
}
</script>
	test
<input type="text" id="test" onclick="addressTest()" readonly/>
<input type="button" id="test" onclick="outTest()">out</button>
<input type="button" id="test" onclick="outTest2()">in</button>

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