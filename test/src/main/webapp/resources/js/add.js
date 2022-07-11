$(function(){
	$(".addfile").click(function(){
		$(".add").append(`
			<div>
				<button type="button" class="deletefile">삭제</button>
				<input type="file" name="files">
			</div>
			`);
			
		$(".deletefile").click(function(){
			$(this).parent().empty('div');
			$(this).parent().remove('div');
		})
	})
})