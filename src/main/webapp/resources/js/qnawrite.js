$(document).ready(function(){
	$("#btnSave").on("click", btnSave);
});

function btnSave(evt){
	evt.preventDefault();
	qnaForm.submit();
}