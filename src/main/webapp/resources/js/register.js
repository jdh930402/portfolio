
$(document).ready(()=>{
	let email, password, rePassword, name, tel;
	// 이메일 유효성 검사
	$("input[name = email]").on("focusout", checkEmail);
	// 비밀번호 유효성 검사
	$("input[name = password]").on("focusout", checkPwd);
	// 비밀번호 확인 유효성 검사
	$("input[name = rePassword]").on("focusout", checkRePwd);
	// 이름 유효성 검사
	$("input[name = name]").on("focusout", checkName);
	// 핸드폰 번호 유효성 검사
	$("input[name = tel]").on("focusout", checkTel);
	// submit 유효성검사
	$("#btnRegister").on("click", checkSubmit);
});


// 이메일 유효성 검사
const checkEmail = ()=>{
	let regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if( $.trim($("input[name = email]").val()) === ""){
		// 공백일 경우
		$("#emailMessage").css("color", "red");
		$("#emailMessage").text("필수 입력사항입니다.");
		email = false;
	}else if( $.trim($("input[name = email]").val()).length > 50 ){
		// 50자를 초과할 경우
		$("#emailMessage").css("color", "red");
		$("#emailMessage").text("이메일은 50자 이내로 입력 가능합니다.");
		email = false;
	}else if(regEmail.test($("input[name = email]").val()) === false && $("input[name = email]").val().length !== 0){
		// 입력값이 있는데 이메일 형식이 아닐 경우
		$("#emailMessage").css("color", "red");
		$("#emailMessage").text("올바른 이메일 형식으로 입력하세요.");
		email = false;
	}else if(regEmail.test($("input[name = email]").val()) === true && $("input[name = email]").val() !== "" && $("input[name = email]").val().length <= 50 ){
		// 조건을 모두 만족할 경우
		$.ajax({
			type : 'post',
			url : 'checkEmail',
			data : $("input[name = email]").val(),
			dataType : 'json',
			contentType: "application/json; charset=UTF-8",
			success : function(json){
				if(json["email"]){
					$("#emailMessage").css("color", "red");
					$("#emailMessage").text("이미 사용중이거나 탈퇴한 이메일입니다.");
					email = false;
				}else{
					$("#emailMessage").css("color", "#777777");
					$("#emailMessage").text("사용 가능한 이메일입니다.");
					email = true;
				}
			},
			error : function(){
				alert('회원가입 오류');
			}
		});
	}
}

// 비밀번호 유효성 검사
const checkPwd = ()=>{
	// 영문+숫자 5~13자리 최소 1개 포함
	let regPass = /^.*(?=.{5,14})(?=.*[0-9])(?=.*[a-zA-Z]).*$/; 
	if($('input[name = password]').val() === ""){
		$("#passwordMessage").css("color", "red");
		$("#passwordMessage").text("필수 입력사항입니다.");
		return false;
	} else if( !regPass.test($('input[name = password]').val())){
		$("#passwordMessage").css("color", "red");
		$("#passwordMessage").text("비밀번호는 영문+숫자 조합으로 5~14자리로 입력하세요");
		return false;
	} else{
		$("#passwordMessage").css("color", "#777777");
		$("#passwordMessage").text("사용 가능한 비밀번호입니다.");
		return true;
	}
}

// 비밀번호 확인 유효성 검사
const checkRePwd = ()=>{
	if($("input[name = rePassword]").val() === ""){
		$("#rePasswordMessage").css("color", "red");
		$("#rePasswordMessage").text("비밀번호를 확인하세요.");
		return false;
	} else if( $('input[name = password]').val() !== $('input[name = rePassword]').val()){
		$("#rePasswordMessage").css("color", "red");
		$("#rePasswordMessage").text("비밀번호가 일치하지 않습니다.");
		return false;
	} else{
		$("#rePasswordMessage").css("color", "#777777");
		$("#rePasswordMessage").text("비밀번호가 일치합니다.");
		return true;
	}
}

// 이름 유효성 검사
const checkName = ()=>{
	if($.trim($("input[name = name]").val()) === ""){
		$("#nameMessage").css("color", "red");
		$("#nameMessage").text("필수 입력사항입니다.");
		return false;
	}else{
		$("#nameMessage").text("");
		return true;
	}
}

// 핸드폰 번호 유효성 검사
const checkTel = ()=>{
	let regTel = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{4})-?([0-9]{4})$/;
	if( $.trim($("input[name = tel]").val()) === ""){
		$("#telMessage").css("color", "red");
		$("#telMessage").text("필수 입력사항입니다.");
		return false;
	}else if( regTel.test($("input[name = tel]").val()) === false ){
		$("#telMessage").css("color", "red");
		$("#telMessage").text("올바른 형식의 핸드폰 번호를 입력하세요.");
		return false;
	}else{
		let trans_tel = $("input[name = tel]").val().replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{4})-?([0-9]{4})$/, "$1-$2-$3");                  
		$("input[name = tel]").val(trans_tel);
		$("#rePasswordMessage").css("color", "#777777");
		$("#telMessage").text("사용 가능한 전화번호입니다.");
		return true;
	}
}

// submit 유효성검사
const checkSubmit = ()=>{
	checkEmail();
	if(!email){
		$("input[name = email]").focus();
		return;
	}else if(!checkPwd()){
		$("input[name = password]").focus();
		return;
	}else if(!checkRePwd()){
		$("input[name = rePassword]").focus();
		return;
	}else if(!checkName()){
		$("input[name = name]").focus();
		return;
	}else if(!checkTel()){
		$("input[name = tel]").focus();
		return;
	}else{
		regForm.submit();
	}
}
