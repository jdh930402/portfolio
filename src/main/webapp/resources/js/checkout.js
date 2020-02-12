$(document).ready(function(){
	$("select[name = addrInfo]").on("change", selectAddr);
	$("input[name = addr1]").on("focus", searchAddr);
	$("#btnSearchAddr").on("click", searchAddr);
	$("#btnAddrList").on("click", addrList);
	$(".tr_content").on("click", clickAddr)
	$("#btnCheckout").on("click", checkout);
});

function addrList(evt){
	evt.preventDefault();
	if( $("select[name = addrInfo] option:selected").val() === "0"){
		window.open("addropener", "배송지목록", "width=580, height=600, resizable=0, scrollbars=yes, status=0, titlebar=0, toolbar=0, left=700, top=200")
	}else{
		alert("등록 배송지 입력시 검색 가능합니다.")
	}
}

function checkout(evt){
	evt.preventDefault();
	if(!checkRecipient()){
		console.log("1")
		$("input[name = recipient]").focus();
		return;
	}else if(!checkTel()){
		console.log("2")
		$("input[name = tel]").focus();
		return;
	}else if(!checkAddr()){
		console.log("3")
		$("input[name = addr3]").focus();
		return;
	}else if(!checkBox()){
		$("input[name = selector]").focus();
		return;
	}else{
		$("#checkout").attr("class", "container invisible");
		$("#loading_area").attr("class", "");
		loading();
	}
}

function loading(){
	let count = 0;
	const timer = function timer(){
	  if (count >= 3){
		  addrForm.action = "purchaseok";
		  addrForm.method = "post";
		  addrForm.submit();
	  }
	  setTimeout(() => {
	    console.log("현재 시각 : ", new Date(), count);
	    count++;
	    timer();
	  }, 1000);
	}
	timer();
}

//유효성 검사
function checkBox(){
	if( !$("input[name = selector]").is(":checked") ){
		$("#checkMessage").css("color", "red");
		$("#checkMessage").text("필수사항을 동의하세요.");
		return false;
	}else{
		$("#checkMessage").text("");
		return true;
	}
}

//수령인 유효성 검사
function checkRecipient(){
	if($.trim($("input[name = recipient]").val()) === ""){
		$("#recipientMessage").text("필수입력 정보입니다.");
		$("#recipientMessage").css("color", "red");
		return false;
	}else{
		$("#recipientMessage").text("");
		return true;
	}
}

const searchAddr = (evt)=>{
	evt.preventDefault();
	if( $("select[name = addrInfo] option:selected").val() === "1" ){
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
	}else{
		alert("신규배송지 입력시 검색 가능합니다.");
	}
}

//주소 유효성 검사
function checkAddr(){
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

//핸드폰 번호 유효성 검사
function checkTel(){
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



// select값 선택시
function selectAddr(){
	if( $("select[name = addrInfo] option:selected").val() === "0"){
		$.ajax({
			type : "post",
			url : "checkaddr",
			dataType : "json",
			contentType: "application/json; charset=UTF-8",
			success : (json)=>{
				$("#name").val(json["defaultAddr"].recipient);
				$("#tel").val(json["defaultAddr"].tel);
				$("#addr1").val(json["defaultAddr"].addr1);
				$("#addr2").val(json["defaultAddr"].addr2);
				$("#addr3").val(json["defaultAddr"].addr3);
			},
			error : ()=>{
				alert("배송지 오류");
			}
		});
	}else{
		$("#name").val("");
		$("#name").removeAttr("readonly");
		
		$("#tel").val("");
		$("#tel").removeAttr("readonly");
		
		$("#addr1").val("");
		$("#addr2").val("");
		$("#addr3").val("");
		$("#addr3").removeAttr("readonly");
		
	}
}

function clickAddr(evt){
	let recipient = $(evt.currentTarget).children().first().text();
	if( recipient.indexOf("기본배송지") !== -1){
		recipient = recipient.substring(0, recipient.indexOf("기본배송지"));
	}
	let addr1 = $(evt.currentTarget).children().next().children().first().text();
	let addr2 = $(evt.currentTarget).children().next().children().first().next().text();
	let addr3 = $(evt.currentTarget).children().next().children().first().next().next().text();
	let tel = $(evt.currentTarget).children().last().text();
	
	opener.$("#name").val(recipient);
	opener.$("#addr1").val(addr1);
	opener.$("#addr2").val(addr2);
	opener.$("#addr3").val(addr3);
	opener.$("#tel").val(tel);
	window.close();
}

