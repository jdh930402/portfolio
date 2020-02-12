$(document).ready(()=>{
	$('select').niceSelect();
	$('.xyz').on('click',function(){
		event();
	});

	$("select[name = categorym_id]").on("change", selectCategorym);
	
	// 추가버튼 클릭시
	$("#btnAddSize").on("click", addSize);
	$("#addSize").on("click", delSize);
	
	$("#mainImage").on("change",fileUpload);
	$("#optionImage").on("change",fileUpload);
	$("#detailImage").on("change",fileUpload);
	
	$("#btnRegister").on("click", regPrd);
	
});

// 상품 등록버튼 클릭시
const regPrd = (evt)=>{
	evt.preventDefault();
	if(checkCategory() && checkBrand() && checkPrdName() && checkPrice() && checkOrigin() && checkMainImg() && checkSizeAmount()){
		regForm.submit();
		alert("상품을 등록하였습니다.")
	}
}

// 유효성 검사
const checkCategory = ()=>{
	if($("select[name = categorym_id] option:selected").val() === "" || $("select[name = categorys_id] option:selected").val() === ""){
		$("#categoryMessage").css("color", "red");
		$("#categoryMessage").text("필수 정보입니다.");
		return false;
	}else{
		$("#categoryMessage").text("");
		return true;
	}
}

const checkBrand = ()=>{
	if($("select[name = brand_id] option:selected").val() === ""){
		$("#brandMessage").css("color", "red");
		$("#brandMessage").text("필수 정보입니다.");
		return false;
	}else{
		$("#brandMessage").text("");
		return true;
	}
}

const checkPrdName = ()=>{
	if($.trim($("input[name = name]").val()) === ""){
		$("#nameMessage").css("color", "red");
		$("#nameMessage").text("필수 정보입니다.");
		return false;
	}else{
		$("#nameMessage").text("");
		return true;
		
	}
}

const checkPrice = ()=>{
	if($("input[name = price]").val() === ""){
		$("#priceMessage").css("color", "red");
		$("#priceMessage").text("필수 정보입니다.");
		return false;
	}else{
		$("#priceMessage").text("");
		return true;
	}
}

const checkOrigin = ()=>{
	if( $("select[name = origin_id] option:selected").val() === ""){
		$("#originMessage").css("color", "red");
		$("#originMessage").text("필수 정보입니다.");
		return false;
	}else{
		$("#originMessage").text("");
		return true;
	}
}

const checkMainImg = ()=>{
	if( $("#mainImage").val() === ""){
		$("#mainImgMessage").css("color", "red");
		$("#mainImgMessage").text("필수 정보입니다.");
		return false;
	}else{
		$("#mainImgMessage").text("");
		return true;
	}
}

const checkSizeAmount = ()=>{
	if( ($("select[name = size_id] option:selected").val() === "0" || $("#amount").val().length === 0) &&  $("input[name = sizeList]").val() === undefined ){
		$("#sizeAmountMessage").css("color", "red");
		$("#sizeAmountMessage").text("필수 정보입니다.");
		return false;
	}else if( $("select[name = size_id] option:selected").val() === "0" && $("#amount").val().length !== 0 &&  $("input[name = sizeList]").val() !== undefined ){
		console.log($("input[name = sizeList]").val());
		$("#sizeAmountMessage").css("color", "red");
		$("#sizeAmountMessage").text("사이즈 또는 재고량을 형식에 맞게 입력하세요.");
		return false;
	}else if( $("select[name = size_id] option:selected").val() !== "0" && $("#amount").val().length === 0 &&  $("input[name = sizeList]").val() !== undefined ){
		console.log($("input[name = sizeList]").val());
		$("#sizeAmountMessage").css("color", "red");
		$("#sizeAmountMessage").text("사이즈 또는 재고량을 형식에 맞게 입력하세요.");
		return false;
	}else{
		$("#sizeAmountMessage").text("");
		return true;
	}
}

	
const fileUpload = (evt)=>{
	let file = $(evt.target).val();
	let fileValue = file.split("\\");
	let fileName = fileValue[fileValue.length-1]; // 파일명
	$(evt.target).next().text(fileName);
}


// 사이즈 제거버튼 클릭시
const delSize = (evt)=>{
	let arrSize = new Array();
	if($(evt.target).attr("type") === "button"){
		$(evt.target).parents("tr").remove();
		
		let sizeIds = $("#addSize").find("input[name=size_id]");
		for(let i=0, size=sizeIds.size() ; i < size ; i++){
			arrSize.push($(sizeIds[i]).val());
		}
		
		$("#amount").val("");
		$("#size").find("option").remove();
		$("#size").append("<option value='0'>선택</option>");
		$("#size").niceSelect("update");
		
		$.ajax({
			type : "post",
			url : "/portfolio/checkSize",
			data : JSON.stringify({"arrSize": arrSize}),
			traditional : true,
			contentType: "application/json; charset=UTF-8",
			success : (json)=>{
				if(json["size"]){
					json["size"].forEach(function(element, index){
						$("#size").append("<option value = '" + element.id + "'>" + element.size + "</option>");
					});
					$("#size").niceSelect("update");
				}
			},
			error : ()=>{
				alert("사이즈 에러");
			}
		});
	}
}

const addSize = (evt)=>{
	evt.preventDefault();
	let innerSize = "";
	if($("select[name = size_id] option:selected").val() !== "0" && $("#amount").val().length !== 0){
		innerSize += "<tr>";
		innerSize += "<td class='td_title'>사이즈/재고량</td>";
		innerSize += "<td colspan='2'>";
		innerSize += "<input type='hidden' name='sizeList' value = '" + $("select[name = size_id] option:selected").val() + "'>";
		innerSize += "<input class='small-input text-center' type='text' name='size' readonly='readonly' value = '" + $("select[name = size_id] option:selected").text() + "'>";
		innerSize += "<span>/</span>";
		innerSize += "<input class='small-input text-center' type='text' readonly='readonly' name='amountList' value = '" + $("#amount").val() + "'>";
		innerSize += "<span>개</span>";
		innerSize += "</td>";
		innerSize += "<td><button type='button' class='btn btn-outline-primary btn-sm btn-option float-right'>제거</button></td>";
		innerSize += "</tr>";
		$("#addSize").append(innerSize);
		$("#size").find("option").each(function() {
		    if(this.value == $("select[name = size_id] option:selected").val()) {
		    	$(this).remove();
		    }
		});
		$("#sizeAmountMessage").text("");
		$("#amount").val("");
		$("#size").niceSelect("update");
	}else{
		$("#sizeAmountMessage").css("color", "red");
		$("#sizeAmountMessage").text("사이즈 또는 재고량을 형식에 맞게 입력하세요.");
		return false;
	}
}

// 상위 카테고리 선택시 하위 카테고리 내용 변경  
const selectCategorym = ()=>{
	if( $("select[name = categorym_id] option:selected").val() !== ""){
		$.ajax({
			type : "get",
			url : "/portfolio/checkCategoryM?categorym_num=" + $("select[name = categorym_id] option:selected").val(),
			dataType : "json",
			success : (data)=>{
				if(data["categorys"]){
					$("select[name = categorys_id] option").remove(); 
					$("select[name = categorys_id]").append("<option value=''>선택</option>");
					data["categorys"].forEach(function(element, index){
						$("select[name = categorys_id]").append("<option value = '" + data["categorys"][index]["id"] + "'>" + data["categorys"][index]["name"] + "</option>");
						$("select[name = categorys_id]").niceSelect("update");
					});
				}
			},
			error : ()=>{
				alert("카테고리 에러");
				
			}
		});
	}else{
		$("select[name = categorys_id] option").remove();
		$("select[name = categorys_id]").append("<option value=''>선택</option>");
		$("select[name = categorys_id]").niceSelect("update");
	}
}


