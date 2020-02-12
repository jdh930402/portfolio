$(document).ready(function(){
	$("tbody").on("click", changeCart);
	$("tbody").on("keyup", changeQuantity);
	$("tbody").on("focusout", quantity);
	$("#btnShopping").on("click", productListMove);
	$("#btnCheckout").on("click", checkoutMove);
});

function productListMove(){
	location.href = "productlist"
}

function checkoutMove(){
	if($("tbody").children().children().attr("class") === "firstTd"){
		location.href = "checkout"
	}else{
		alert("장바구니에 물품을 추가하세요.");
	}
}

function quantity(evt){
	if(evt.target.name === "quantity" && $(evt.target).val() === ""){
		id = $(evt.target).parent().children().val();
		quantity = 1;
		$(evt.target).val(quantity);
		quantityAjax(evt, id, quantity);
	}
}

function changeCart(evt){
	evt.preventDefault();
	let id, size, quantity;
	
	if( $(evt.target).closest("td").attr("class") === "firstTd" ){
		location.href = $(evt.currentTarget).children().children().children().attr("href");
	}
	
	if(evt.target.id === "btnUp"){
		id = $(evt.target).parent().children().val();
		quantity = parseInt($(evt.target).prev().val());
		if(isNaN(quantity)){
			quantity = 1;
			$(evt.target).prev().val(quantity);
		}else{
			$(evt.target).prev().val(++quantity);
		}
		quantityAjax(evt, id, quantity);
	}else if(evt.target.parentNode.id === "btnUp"){
		id = $(evt.target).parent().parent().children().val();
		quantity = parseInt($(evt.target).parent().prev().val());
		if(isNaN(quantity)){
			quantity = 1;
			$(evt.target).parent().prev().val(quantity);
		}else{
			$(evt.target).parent().prev().val(++quantity);
		}
		quantityAjax(evt, id, quantity);
	}
		
	if(evt.target.id === "btnDown"){
		id = $(evt.target).parent().children().val();
		quantity = parseInt($(evt.target).prev().prev().val());
		if(quantity > 1){
			$(evt.target).prev().prev().val(--quantity);
		}else if(isNaN(quantity)){
			quantity = 1;
			$(evt.target).prev().prev().val(quantity);
		}else{
			return;
		}
		quantityAjax(evt, id, quantity);
	}else if(evt.target.parentNode.id === "btnDown"){
		id = $(evt.target).parent().parent().children().val();
		quantity = parseInt($(evt.target).parent().prev().prev().val());
		if( quantity > 1){
			$(evt.target).parent().prev().prev().val(--quantity);
		}else if(isNaN(quantity)){
			quantity = 1;
			$(evt.target).parent().prev().prev().val(quantity);
		}else{
			return;
		}
		quantityAjax(evt, id, quantity);
	}
	
	if($(evt.target).attr("class") === "genric-btn default-border"){
		id = $(evt.target).parent().prev().prev().children().children().val();
		deleteProduct(id);
	}
		
}

function changeQuantity(evt){

	if($(evt.target).attr("name") === "quantity" && $(evt.target).val() > 999){
		id = $(evt.target).prev().val();
		quantity = 999;
		$(evt.target).val(quantity);
		quantityAjax(evt, id, quantity);
	}else if($(evt.target).attr("name") === "quantity" && $(evt.target).val() < 1 && $(evt.target).val() !== ""){
		id = $(evt.target).prev().val();
		quantity = 1;
		$(evt.target).val(quantity);
		quantityAjax(evt, id, quantity);
	}else if($(evt.target).attr("name") === "quantity" && $(evt.target).val() !== ""){
		id = $(evt.target).prev().val();
		quantity = $(evt.target).val();
		quantityAjax(evt, id, quantity);
	}else if($(evt.target).attr("name") === "quantity" && $(evt.target).val() === "" ){
		$(evt.target).parent().parent().next().children().text("0원");
		$("#totalPrice").text("0원");
	}
}

function quantityAjax(evt, id, quantity){
	$.ajax({
		type : 'post',
		url : 'changeQuantity',
		data : JSON.stringify({"id" : id, "quantity" : quantity}),
		dataType : 'json',
		contentType: "application/json; charset=UTF-8",
		success : function(json){
			if(json["isSuccess"] > 0){
				let oriPrice, oriTotal;
				let newPrice = parseInt(json["quantityNPrice"].quantity) * parseInt(json["quantityNPrice"].price);
				
				if(evt.target.id === "btnUp" || evt.target.id === "btnDown" || evt.target.name === "quantity"){
					oriPrice = parseInt($(evt.target).parent().parent().next().children().text().replace(/,/g,""));
					$(evt.target).parent().parent().next().children().text(String(newPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원");
				}else if(evt.target.parentNode.id === "btnUp" || evt.target.parentNode.id === "btnDown"){
					oriPrice = parseInt($(evt.target).parent().parent().parent().next().children().text().replace(/,/g,""));
					$(evt.target).parent().parent().parent().next().children().text(String(newPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원");
				}
				
				oriTotal = parseInt($("#totalPrice").text().replace(/,/g,""));
				$("#totalPrice").text( String(oriTotal - oriPrice + newPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원" );
			}
		},
		error : function(){
			alert("수량 변경 Ajax에러");
		}
	});
}

function deleteProduct(id){
	$.ajax({
		type : 'post',
		url : 'deleteProduct',
		data : JSON.stringify({"id" : id}),
		dataType : 'json',
		contentType: "application/json; charset=UTF-8",
		success : function(json){
			if(json["deleteProduct"] === true){
				location.href = "cartlist"
			}
		},
		error : function(){
			alert("장바구니 상품삭제 Ajax에러");
		} 
	});
}
