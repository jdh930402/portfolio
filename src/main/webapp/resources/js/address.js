$(document).ready(()=>{
	$("#btnAdd").on("click", addAddr);
	
	$("input[name = recipient]").on("focusout", checkRecipient);
	$("input[name = addr3]").on("focusout", checkAddr);
	$("input[name = tel]").on("focusout", checkTel);
	
	$("input[name = addr1]").on("focus", searchAddr);
	$("#btnSearch").on("click", searchAddr);
	$("#btnSave").on("click", saveAddr);
});
// 배송지 수정
const updateFunc = (id)=>{
	location.href = "/portfolio/addressManage/modify?id=" + id;
}

// 배송지 삭제
const deleteFunc = (id)=>{
	if(confirm("배송지를 삭제하시겠습니까?")){
		location.href = "/portfolio/addressManage/delete?id=" + id;
	}
}


// 배송지 추가 버튼 클릭시
const addAddr = (evt)=>{
	evt.preventDefault();
	$.ajax({
		type : "post",
		url : "/portfolio/addressManage/checkAddrLength",
		dataType : "json",
		contentType: "application/json; charset=UTF-8",
		success : (json)=>{
			if(json["addrLength"] > 4 ){
				$("#addrMessage").css("color", "red");
				$("#addrMessage").text("입력개수 초과 (주소지는 최대 5개 등록 가능합니다.)");
				
			}else{
				location.href = "/portfolio/addressManage/add";
			}
		},
		error : ()=>{
			alert("관리자 로그인 에러");
		}
	});
	
}

const searchAddr = (evt)=>{
	evt.preventDefault();
	$("input[name = addr2]").focus();
	new daum.Postcode({
		oncomplete: function(data) {
			//data는 사용자가 선택한 주소 정보를 담고 있는 객체이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
			$("input[name = addr1]").val(data["zonecode"]);
			$("input[name = addr2]").val(data["address"]);
		},
		onclose: function(state) {
	        //state는 우편번호 찾기 화면이 어떻게 닫혔는지에 대한 상태 변수 이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
	        if(state === 'FORCE_CLOSE'){
	            //사용자가 브라우저 닫기 버튼을 통해 팝업창을 닫았을 경우, 실행될 코드를 작성하는 부분입니다.
	        	$("input[name = addr2]").focus();
	        	return;
	        } else if(state === 'COMPLETE_CLOSE'){
	            //사용자가 검색결과를 선택하여 팝업창이 닫혔을 경우, 실행될 코드를 작성하는 부분입니다.
	            //oncomplete 콜백 함수가 실행 완료된 후에 실행됩니다.
	        	$("input[name = addr3]").focus();
	        	return;
	        }
	    }
	}).open();
}

// 수령인 유효성 검사
const checkRecipient = ()=>{
	if($.trim($("input[name = recipient]").val()) === ""){
		$("#recipientMessage").text("필수입력 정보입니다.");
		$("#recipientMessage").css("color", "red");
		return false;
	}else{
		$("#recipientMessage").text("");
		return true;
	}
}

// 주소 유효성 검사
const checkAddr = ()=>{
	if( $.trim($("input[name = addr3]").val()) === "" && ($.trim($("input[name = addr1]").val()) === "" || $.trim($("input[name = addr2]").val()) === "") ){
		$("#addrMessage").text("필수 입력사항입니다.");
		$("#addrMessage").css("color", "red");
		return false;
	}else if($.trim($("input[name = addr1]").val()) === "" || $.trim($("input[name = addr2]").val()) === "" ){
		$("#addrMessage").text("우편번호와 기본주소를 입력하세요.");
		$("#addrMessage").css("color", "red");
		return false;
	}else if($.trim($("input[name = addr3]").val()) === ""){
		$("#addrMessage").text("상세주소를 입력하세요.");
		$("#addrMessage").css("color", "red");
		return false;
	}else{
		$("#addrMessage").text("");
		return true;
	}
}

// 핸드폰 번호 유효성 검사
const checkTel = ()=>{
	let regTel = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{4})-?([0-9]{4})$/;
	if( $.trim($("input[name = tel]").val()) === ""){
		$("#telMessage").text("필수 입력사항입니다.");
		$("#telMessage").css("color", "red");
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

// 배송지 추가 작성 후 저장 버튼 클릭시 
const saveAddr = (evt)=>{
	evt.preventDefault();
	if(!checkRecipient()){
		$("input[name = recipient]").focus();
		return;
	}else if(!checkAddr()){
		$("input[name = addr3]").focus();
		return;
	}else if(!checkTel()){
		$("input[name = tel]").focus();
		return;
	}else{
		addrForm.submit();
	}
}
