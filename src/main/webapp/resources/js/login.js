$(document).ready(()=>{
	$("input[name = email]").on("focusout", checkEmail);
	$("input[name = password]").on("focusout", checkPwd);
	$("input[name = email]").on("keydown", checkEnter);
	$("input[name = password]").on("keydown", checkEnter);
	$("#btnLogin").on("click", login);
});


//이메일 유효성 검사
const checkEmail = ()=>{
	let regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if( $.trim($("input[name = email]").val()) === ""){
		// 공백일 경우
		$("#emailMessage").css("color", "red");
		$("#emailMessage").text("이메일을 입력하세요.");
		return false;
	}else if( $.trim($("input[name = email]").val()).length > 50 ){
		// 50자를 초과할 경우
		$("#emailMessage").css("color", "red");
		$("#emailMessage").text("이메일은 50자 이내로 입력 가능합니다.");
		return false;
	}else if(regEmail.test($("input[name = email]").val()) === false && $("input[name = email]").val().length !== 0){
		// 입력값이 있는데 이메일 형식이 아닐 경우
		$("#emailMessage").css("color", "red");
		$("#emailMessage").text("올바른 이메일 형식으로 입력하세요.");
		return false;
	}else if(regEmail.test($("input[name = email]").val()) === true && $("input[name = email]").val() !== "" && $("input[name = email]").val().length <= 50 ){
		// 조건을 모두 만족할 경우
		$("#emailMessage").text("");
		return true;
	}
}

// 비밀번호 유효성 검사
const checkPwd = ()=>{
	if( $("input[name = password]").val() === "" ){
		$("#passwordMessage").css("color", "red");
		$("#passwordMessage").text("비밀번호를 입력하세요.");
		return false;
	}else{
		$("#passwordMessage").text("");
		return true;
	}
}

const login = ()=>{
	if(!checkEmail()){
		$("input[name = email]").focus();
		return;
	}else if(!checkPwd()){
		$("input[name = password]").focus();
		return;
	}else{
		$.ajax({
			type : 'post',
			url : 'loginCheck',
			data : JSON.stringify({"email" : $("input[name = email]").val(), "password" : $("input[name = password]").val()}),
			dataType : 'json',
			contentType: "application/json; charset=UTF-8",
			success : function(json){
				if(json["user"] == null ){
					$("#checkLogin").css("color", "red");
					$("#checkLogin").text("가입하지 않은 이메일이거나, 잘못된 비밀번호입니다.");
				}else{
					loginForm.submit();					
				}
			},
			error : function(){
				alert('로그인 오류');
			}
		});
	}
	
}

const checkEnter = (key)=>{
	if(key.keyCode == 13){
		login();
	}
}