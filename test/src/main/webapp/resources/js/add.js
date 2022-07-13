$(function(){
	var count=0;
	$(".addfile").click(function(){
		count++;
		if(count>2){
			alert("3개까지의 엑셀만 등록 가능합니다.");
			return;
		}
		console.log(count);
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
})
