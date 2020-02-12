$(document).ready(()=>{
	$("input[name = id]").on("focusout", checkId);
	$("input[name = password]").on("focusout", checkPwd);

	$("input[name = id]").on("keydown", checkEnter);
	$("input[name = password]").on("keydown", checkEnter);
	$("#btnLogin").on("click", login);
});

const checkEnter = (key)=>{
	if(key.keyCode == 13){
		login();
	}
}
const checkId = ()=>{
	if($.trim($("input[name = id]").val()) === ""){
		$("#idMessage").css("color", "red");
		$("#idMessage").text("아이디를 입력하세요.");
		return false;
	}else{
		$("#idMessage").text("");
		return true;
	}
}

const checkPwd = ()=>{
	if($("input[name = password]").val() === ""){
		$("#passwordMessage").css("color", "red");
		$("#passwordMessage").text("비밀번호를 입력하세요.");
		return false;
	}else{
		$("#passwordMessage").text("");
		return true;
	}
}


const login = ()=>{
	if(!checkId()){
		$("input[name = id]").focus();
		return;
	}else if(!checkPwd()){
		$("input[name = password]").focus();
		return;
	}else{
		$.ajax({
			type : "post",
			data : JSON.stringify({ "id" : $("input[name = id]").val(), "password" : $("input[name = password]").val()}),
			url : "/portfolio/admin/checkLogin",
			dataType : "json",
			contentType: "application/json; charset=UTF-8",
			success : (json)=>{
				if(json["admin"] === null ){
					$("#checkLogin").css("color", "red");
					$("#checkLogin").text("올바른 관리자 계정이 아닙니다.");
				}else{
					loginForm.submit();
				}
			},
			error : ()=>{
				alert("관리자 로그인 에러");
			}
		});
	}
}
