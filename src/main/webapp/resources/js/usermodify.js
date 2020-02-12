let emailSwitch = nameSwitch = telSwitch = false;
let email;
let emailVal, telVal;

$(document).ready(()=>{
	$("#btnSubmit").on("click", checkSubmit);
	$("input[name = password]").on("keydown", checkEnter);
	// ========================================
	$("#userModify-table").on("click", modifyUserData);
	$("[name = email]").on("keydown", checkEnter);
	$("#btnCancel").on("click", cancel);
	$("#btnOk").on("click", withdrawal);
	$('.modal').on('hidden.bs.modal', modalCancel);
});

// ==========================================================================
// 회원정보 확인에 비밀번호 유효성검사
const checkSubmit = function(evt){
	evt.preventDefault();
	if( $("input[name = password]").val() === ""){
		$("#passwordMessage").css("color", "red");
		$("#passwordMessage").text("비밀번호를 입력하세요.");
		$("input[name = password]").focus();
		return;
	}else{
		$.ajax({
			type : 'post',
			url : 'modifyCheck',
			data : JSON.stringify({"password" : $("input[name = password]").val()} ),
			dataType : 'json',
			contentType: "application/json; charset=UTF-8",
			success : function(json){
				if(json["user"] === null){
					$("#passwordMessage").css("color","red");
					$("#passwordMessage").text("비밀번호가 일치하지 않습니다.");
					$("input[name = password]").focus();
					return;
				}else{
					modifyForm.submit();
				}
			},
			error : function(){
				alert('유저확인 오류');
			}
		});
	}
}


/* 공용
 * =================================================================
 */ 

// 회원정보 확인에서 비밀번호 확인시 엔터 이벤트
const checkEnter = (evt)=>{
	if(evt.keyCode == 13){
		evt.preventDefault();
		if( $(evt.target).attr("id") === "checkPass" ){
			checkSubmit(evt);
		}
	}
}

// =================================================================

const modifyUserData = (evt)=>{
	evt.preventDefault();
	// 아이디 수정 및 저장
	if( $(evt.target).attr("id") === "btnEmail" ){
		if(!emailSwitch){
			$("#emailMessage").text("");
			emailVal = $("input[name = email]").val();
			$("input[name = email]").focus();
			$("input[name = email]").val("");
			$("input[name = email]").removeAttr("readonly");
			$("input[name = email]").removeClass("content-input");
			$("input[name = email]").on("focusout", checkEmail);
			$("#btnEmail").text("저장");
			$("#btnEmailCancel").removeClass("display-none");
			emailSwitch = true;
		}else{
			if(email){
				$.ajax({
					type : 'post',
					url : 'modifyEmail',
					data : JSON.stringify({"newEmail" : $("input[name = email]").val()} ),
					dataType : 'json',
					contentType: "application/json; charset=UTF-8",
					success : function(json){
						if(json["email"] != null){
							$("input[name = email]").val(json["email"]);
							$("input[name = email]").off("focusout", checkEmail);
							$("#emailMessage").text("이메일을 수정하였습니다.");
							$("#btnEmail").text("수정");
							$("#btnEmailCancel").addClass("display-none");
							$("input[name = email]").attr("readonly","readonly");
							$("input[name = email]").addClass("content-input");
							emailSwitch = false;
						}
					},
					error : function(){
						alert('이메일 수정 오류');
					}
				});
			}else{
				$("input[name = email]").focus();
				return;
			}
		}
	}
	
	// 비밀번호 수정전 비밀번호 체크
	if( $(evt.target).attr("id") === "btnPwd"){
		$.ajax({
			type : 'post',
			url : 'checkPassword',
			data : JSON.stringify({"password" : $("input[name = password]").val()} ),
			dataType : 'json',
			contentType: "application/json; charset=UTF-8",
			success : function(json){
				if(json["password"]){
					$(".modify-pwd").addClass("display-none");
					$(".modify-rePwd").removeClass("display-none");
					$(".modify-btnGroup").removeClass("display-none");
					$("#passwordMessage").text("");
				}else{
					checkPwd();
				}
			},
			error : function(){
				alert('비밀번호 오류');
			}
		});
	}
	
	// 수정한 비밀번호 저장
	if( $(evt.target).attr("id") === "btnPwdSave"){
		$("#passwordMessage").text("");
		if(!checkNewPwd()){
			$("input[name = newPassword]").focus();
			return;
		} else if(!checkNewRePwd()){
			$("input[name = rePassword]").focus();
			return;
		}
		$("#passwordMessage").text("");
		if(checkNewPwd() && checkNewRePwd()){
			if( $("input[name = password]").val() === $("input[name = newPassword]").val() && $("input[name = newPassword]").val() === $("input[name = rePassword]").val() ){
				$("#passwordMessage").css("color","red");
				$("#passwordMessage").text("동일한 비밀번호로 변경 불가합니다.");
			}else{
				$.ajax({
					type : 'post',
					url : 'modifyPassword',
					data : JSON.stringify({"password" : $("input[name = newPassword]").val()} ),
					dataType : 'json',
					contentType: "application/json; charset=UTF-8",
					success : function(json){
						if(json["password"]){
							$("input[name = password]").val("");
							$("input[name = newPassword]").val("")
							$("input[name = rePassword]").val("")
							$(".modify-pwd").removeClass("display-none");
							$(".modify-rePwd").addClass("display-none");
							$(".modify-btnGroup").addClass("display-none");
							$("#passwordMessage").css("color","#777777");
							$("#passwordMessage").text("비밀번호가 변경되었습니다.");
							$("input[name = newPassword]").val("");
							$("input[name = rePassword]").val("");
						}
					},
					error : function(){
						alert('비밀번호 오류');
					}
				});
			}
		}
	}
	
	// 핸드폰번호 수정 및 저장
	if( $(evt.target).attr("id") === "btnTel"){
		if(!telSwitch){
			$("#telMessage").text("");
			telVal = $("input[name = tel]").val();
			$("input[name = tel]").focus();
			$("input[name = tel]").val("");
			$("input[name = tel]").removeAttr("readonly");
			$("input[name = tel]").removeClass("content-input");
			$("input[name = tel]").on("focusout", checkTel);
			$("#btnTel").text("저장");
			$("#btnTelCancel").removeClass("display-none");
			telSwitch = true;
		}else{
			if(checkTel()){
				if( $("input[name = tel]").val() === telVal ){
					$("#telMessage").css("color", "red");
					$("#telMessage").text("동일한 핸드폰 번호입니다.");
					$("input[name = tel]").focus();
					return;
				}else{
					$.ajax({
						type : 'post',
						url : 'modifyTel',
						data : JSON.stringify({"tel" : $("input[name = tel]").val()} ),
						dataType : 'json',
						contentType: "application/json; charset=UTF-8",
						success : function(json){
							if(json["tel"]){
								$("input[name = tel]").off("focusout", checkTel);
								$("input[name = tel]").attr("readonly","readonly");
								$("input[name = tel]").addClass("content-input");
								$("#btnTel").text("수정");
								$("#btnTelCancel").addClass("display-none");
								$("#telMessage").css("color","#777777");
								$("#telMessage").text("핸드폰번호가 변경되었습니다.");
								telSwitch = false;
							}
						},
						error : function(){
							alert('핸드폰번호 수정 오류');
						}
					});
				}
			}else{
				$("input[name = tel]").focus();
				return;
			}
		}
		
	}
	
	// 이메일 수정 취소
	if( $(evt.target).attr("id") === "btnEmailCancel"){
		$("input[name = email]").off("focusout", checkEmail);
		$("input[name = email]").val(emailVal);
		$("input[name = email]").attr("readonly", "readonly");
		$("#emailMessage").text("");
		$("#btnEmail").text("수정");
		$("#btnEmailCancel").addClass("display-none");
		$("input[name = email]").addClass("content-input");
		emailSwitch = false;
	}
	
	// 비밀번호 수정 취소
	if( $(evt.target).attr("id") === "btnPwdCancel"){
		$("input[name = password]").val("");
		$("input[name = newPassword]").val("")
		$("input[name = rePassword]").val("")
		$(".modify-pwd").removeClass("display-none");
		$(".modify-rePwd").addClass("display-none");
		$(".modify-btnGroup").addClass("display-none");
		$("#passwordMessage").text("");
		$("#rePasswordMessage").text("");
		$("#newPasswordMessage").text("");
	}
	
	// 전화번호 수정 취소
	if( $(evt.target).attr("id") === "btnTelCancel"){
		$("#telMessage").text("");
		$("input[name = tel]").val(telVal);
		$("input[name = tel]").attr("readonly","readonly");
		$("input[name = tel]").addClass("content-input");
		$("input[name = tel]").off("focusout", checkTel);
		$("#btnTel").text("수정");
		$("#btnTelCancel").addClass("display-none");
		telSwitch = false;
	}
}

// 이메일 유효성 검사
const checkEmail = ()=>{
	let regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if( $.trim($("input[name = email]").val()) === ""){
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
				if(json["email"] > 0){
					$("#emailMessage").css("color", "red");
					$("#emailMessage").text("이미 사용중인 이메일입니다.");
					email = false;
				}else{
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

// 비밀번호 체크 유효성 검사
const checkPwd = ()=>{
	if( $("input[name = password]").val() === "" ){
		$("#passwordMessage").css("color", "red");
		$("#passwordMessage").text("비밀번호를 입력하세요.");
		$("input[name = password]").focus();
		return false;
	}else{
		$("#passwordMessage").css("color", "red");
		$("#passwordMessage").text("비밀번호가 일치하지 않습니다.");
		$("input[name = password]").focus();
		return true;
	}
}

// 신규 비밀번호 유효성 검사
const checkNewPwd = ()=>{
	// 영문+숫자 5~13자리 최소 1개 포함
	let regPass = /^.*(?=.{5,14})(?=.*[0-9])(?=.*[a-zA-Z]).*$/; 
	if($('input[name = newPassword]').val() === ""){
		$("#newPasswordMessage").css("color", "red");
		$("#newPasswordMessage").text("필수 입력사항입니다.");
		return false;
	} else if( !regPass.test($('input[name = newPassword]').val())){
		$("#newPasswordMessage").css("color", "red");
		$("#newPasswordMessage").text("비밀번호는 영문+숫자 조합으로 5~14자리로 입력하세요");
		return false;
	} else{
		$("#newPasswordMessage").text("");
		return true;
	}
}


// 비밀번호 확인 유효성 검사
const checkNewRePwd = ()=>{
	if($("input[name = rePassword]").val() === ""){
		$("#rePasswordMessage").css("color", "red");
		$("#rePasswordMessage").text("비밀번호 확인을 입력하세요.");
		return false;
	}else if( $('input[name = newPassword]').val() !== $('input[name = rePassword]').val()){
		$("#rePasswordMessage").css("color", "red");
		$("#rePasswordMessage").text("비밀번호가 일치하지 않습니다.");
		return false;
	} else{
		$("#rePasswordMessage").text("");
		return true;
	}
}

// 핸드폰 유효성 검사
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
		$("#telMessage").text("");
		return true;
	}
}

// 나가기 버튼
const cancel = (evt)=>{
	evt.preventDefault();
	location.href = "/portfolio/login/userModify";
}

// 회원 탈퇴 버튼
const withdrawal = ()=>{
	// 비밀번호 확인
	$.ajax({
		type : 'post',
		url : 'checkPassword',
		data : JSON.stringify({"password" : $("input[name = modalPassword]").val()} ),
		dataType : 'json',
		contentType: "application/json; charset=UTF-8",
		success : function(json){
			if(!json["password"]){
				modalPassCheck();
			}else{
				modalForm.submit();
			}
		},
		error : function(){
			alert('비밀번호 오류');
		}
	});
}

// 회원탈퇴시 모달에 비밀번호 유효성 체크
const modalPassCheck = ()=>{
	if( $("input[name = modalPassword]").val() === "" ){
		$("#modalMessage").css("color", "red");
		$("#modalMessage").text("비밀번호를 입력하세요.");
		$("input[name = modalPassword]").focus();
		return false;
	}else{
		$("#modalMessage").css("color", "red");
		$("#modalMessage").text("비밀번호가 일치하지 않습니다.");
		$("input[name = modalPassword]").focus();
		return false;
	}
}

const modalCancel = ()=>{
	$("input[name = modalPassword]").val("");
}