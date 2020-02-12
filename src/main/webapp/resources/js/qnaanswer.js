$(document).ready(function(){
	$("#btnSave").on("click", qnaSave);
	$("#btnDelete").on("click", qnaDelete);
});

function qnaSave(evt){
	evt.preventDefault();
	if( $("textarea[name = content]").val() === ""){
		alert("내용을 입력하세요.");		
	}else{
		//ajax 입력하기
		$.ajax({
			url : "/portfolio/qnamanager/qnaexist",
			type : "post",
			data : JSON.stringify({"id" : $("input[name = id]").val()}),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(json){
				if(json["qna"].is_answer === "0"){
					alert("답변 등록을 완료하였습니다.");
				}else if(json["qna"].is_answer === "1"){
					alert("답변 수정을 완료하였습니다.");
				}
			},
			error : function(){
				alert("Ajax error");
			}
		});
	}
	qnaForm.submit();
}

function qnaDelete(evt){
	evt.preventDefault();
	if(confirm("삭제하시겠습니까?")){
		$.ajax({
			url : "/portfolio/qnamanager/deleteanswer",
			type : "post",
			data : JSON.stringify({ "id" : $("input[name = id]").val(), "gnum" : $("input[name = gnum]").val()}),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(json){
				if(json["qna"] === true){
					alert("답변 삭제가 완료되었습니다.");
					location.href = "/portfolio/qnamanager";
				}
			},
			error : function(){
				alert("Ajax error");
			}
		});
	}
}