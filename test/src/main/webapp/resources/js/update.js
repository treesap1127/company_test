function test() {
	var regs = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{1,32}$/;//32자의 한굴/영문만 입력하는 정규식
	var regx = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{1,1000}$/;//1000자의 한굴/영문만 입력하는 정규식
	  if (!regs.test($("#name").val())) {        
	  	alert("제목은 한글/영문/숫자 32자까지만 입력 가능합니다.");        
	  	$("#name").focus();
	  	}
	  else if(!regx.test($("#info").val())){
		alert("제목은 한글/영문/숫자 1000자까지만 입력 가능합니다.");        
	  	$("#info").focus();
	}
		else{
			update_form.submit();
		}
}
$(function(){
	var count=$(".upfile").length;
	if(count==1){
		$(".deletefile").remove();
	}
	$(".addfile").click(function(){
		count++;
		console.log(count+"갯수");
		if(count>=3){
			count--;
			alert("3개까지의 엑셀만 등록 가능합니다.");
			return;
		}
		$(".add").append(`
			<div>
				<input type="file" name="files">
			</div>
			`);
			
		$(".deletefile").click(function(){
			$(this).parent().empty('div');
			$(this).parent().remove('div');
			count--;
			console.log(count);
		})
	})
	$(".deletefile").click(function(){
		
		filecode=$(this).attr('id');
		filecode=filecode.replace("file_","");
		$.ajax({
        url:"/basic1/filedelete",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(filecode),
        //서버로 보내는 데이터 타입
        success: result => {
				if(`${result}==1`){
					$(this).siblings().remove();	
					$(this).remove();
					count--;
					if(count==1){
						$(".deletefile").remove();
					}
				}
				else{
					alert("삭제 실패하였습니다.");
				}
		},
        error: (xhr, result) => console.log(`[실패] print`)
        });
		
	})
})

