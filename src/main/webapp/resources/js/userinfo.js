$(document).ready(()=>{
	// 모달에서 확인을 눌렀을 때
	$("#btnOk").on("click", checkAdmin);
	// 모달에서 확인을 눌렀을 때
	$("#btnNo").on("click", cancelModal);
	
	// 유저 이메일을 모달에 전달
	$("tbody").on("click", addEmail);
});

const addEmail = (evt)=>{
	if( $(evt.target).attr("class") === "btnModal" || $(evt.target).attr("class") === "fa fa-trash-o"){
		let email = $(evt.target).closest("tr").children().first().next().text();
		$("input[name = email]").val(email);
	}
}

const cancelModal = ()=>{
	$("input[name = modalPassword]").val("");
}

const checkAdmin = (evt)=>{
	if(!checkPwd()){
		$("input[name = password]").focus();
		return;
	}else{
		$.ajax({
			type : "post",
			data : JSON.stringify({"password" : $("input[name = modalPassword]").val()}),
			url : "checkAdmin",
			dataType : "json",
			contentType: "application/json; charset=UTF-8",
			success : (json)=>{
				if(json["admin"] === null ){
					$("#modalMessage").css("color", "red");
					$("#modalMessage").text("비밀번호가 일치하지 않습니다.");
				}else{
					modalForm.submit();
					alert("해당 회원이 강제탈퇴 되었습니다.")
				}
			},
			error : ()=>{
				alert("관리자 로그인 에러");
			}
		});
	}
}

const checkPwd = ()=>{
	if($("input[name = password]").val() === ""){
		$("#modalMessage").css("color", "red");
		$("#modalMessage").text("비밀번호를 입력하세요.");
		return false;
	}else{
		$("#modalMessage").text("");
		return true;
	}
}