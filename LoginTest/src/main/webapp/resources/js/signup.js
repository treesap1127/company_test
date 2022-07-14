function check_id_Async() {
	var id=$("#id").val();
	$.ajax({
        url:"/member/idcheck",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(id),
               //서버로 보내는 데이터 타입
        success: result => {
				if(result==true){
					alert("사용가능한 아이디입니다.");
					$("#check").attr('value','true');	
				}
				else{
					alert("사용가능 불가능한 아이디입니다.");
				}
				console.log(result);				
	
},
        error: (xhr, result) => console.log(`[실패] print`)
        });
}
function test() {
	  var reg = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}/; //숫자만 입력하는 정규식
	  if (!reg.test(tel.value)) {
	    alert("전화번호를 010-1234-1234 형식으로 입력해주세요");
	    mobile.focus();
	    return false;
	  }
		else if(!$("#check").attr('value')||!$("#name").val()){
			alert("아이디 중복확인과 모든 사항을 입력해주세요");	
		}
		else{
			signup_form.submit();
		}
}
$(function(){
	 $('#id').change( function() {
		$("#check").attr('value','');
	});
});

