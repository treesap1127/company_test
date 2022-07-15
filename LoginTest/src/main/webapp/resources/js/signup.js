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
		var regs = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|\*]{1,32}$/;//32자의 한굴/영문만 입력하는 정규식
	  if (!reg.test(tel.value)) {
	    alert("전화번호를 010-1234-1234 형식으로 입력해주세요");
	    tel.focus();
	  }
	  	else if (!regs.test($("#name").val())) {        
	  	alert("닉네임은 한글/영문 32자만 입력 가능합니다.");        
	  	$("#name").focus();
	  	}
	  	//아이디 체크 및 다른 값들이 다 들어왔는지.
		else if(!$("#check").attr('value')){
			alert("아이디 중복확인을 부탁드립니다!");	
		}
		else{
			signup_form.submit();
		}
}
$(function(){// 중복확인 시 아이디 변경하면 check빼기
	 $('#id').change( function() {
		$("#check").attr('value','');
	});
});

