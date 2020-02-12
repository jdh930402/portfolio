$(document).ready(function(){
	$("#btnCart").on("click", addToCart);
	$("#btnPurchase").on("click", imdPurchase);
	$("#btnQnaWrite").on("click", QnaWrite);
	$("#qnaArea").on("click", showAnswer);
	$("#title").on("click", title);
});

let url = location.search;
let prd_id = new URLSearchParams(url).get("id");

// 답변을 보기위해 클릭한 경우
function showAnswer(evt){
	evt.preventDefault();
	if(evt.target.tagName === "A"){
		if($(evt.target).parent().parent().next().children().attr("class") !== "answer-td"){
			answerAjax(evt);
		}else{
			$(evt.target).parent().parent().next().children().remove();
		}
	}
}
function answerAjax(evt){
	$.ajax({
		type : 'post',
		url : 'qnaanswer',
		data : JSON.stringify({ "id" : $(evt.target).prev().prev().val(), "gnum" : $(evt.target).prev().val()}),
		dataType : 'json',
		contentType: "application/json; charset=UTF-8",
		success : function(json){
			let answer = "";
			answer += "<td colspan='5' class = 'answer-td'>";
			answer += "<strong>문의 제목</strong> - " + json["qna"].title + "<br>";
			answer += "<strong>문의 내용</strong> - " + json["qna"].content + "<br>";
			
			if(json["answer"] != null){
				answer += "<hr class='short-line'>";
				answer += "<strong>답변 내용</strong> - " + json["answer"].content + "<br>";
			}
			answer += "</td>";
			$(evt.target).parent().parent().next().append(answer);
		},
		error : function(){
			alert('답변보기 Ajax error');
		}
	});
}


// qna 버튼을 누른 경우
function QnaWrite(evt, page){
	evt.preventDefault();
	location.href = "qnawrite?product_id=" + prd_id;
}

// 장바구니 추가 버튼을 누른 경우
function addToCart(evt){
	evt.preventDefault();
	if( $("select[name = size_id] option:selected").val() != "" && $("select[name = size_id] option:selected").text().indexOf("품절") === -1){
		$.ajax({
			type : 'post',
			url : 'addcart',
			data : JSON.stringify({"product_id" :  prd_id, "quantity" : $("input[name = qty]").val(), "size_id" : $("select[name = size_id] option:selected").val()}),
			dataType : 'json',
			contentType: "application/json; charset=UTF-8",
			success : function(json){
				if(json["isSuccess"] > 0){
					location.href = "cartlist";
				}
			},
			error : function(){
				alert('장바구니 담기 오류');
			}
		});
	}else{
		alert("사이즈를 선택하세요.");
	}
}

// 즉시구매 버튼을 누른 경우
function imdPurchase(evt){
	evt.preventDefault();
	if($("select[name = size_id] option:selected").val() != "" && $("select[name = size_id] option:selected").text().indexOf("품절") === -1){
		location.href = $("#btnPurchase").attr("href") + "&size_id=" + $("select[name = size_id] option:selected").val();
	}else{
		alert("사이즈를 선택하세요.");
	}
}


/* 페이징 처리 구간 Start */
function checkForHash(page){
	document.location.hash = "#" + page;
    if(document.location.hash){
        var HashLocationName = document.location.hash;
        HashLocationName = HashLocationName.replace("#","");
    }
}

const pageLoad = function(page){
	checkForHash(page);
	pagingAjax(page);
}

let qnaId = $("input[name = id]").val();
let qnaGnum = $("input[name = gnum]").val();
function pagingAjax(page){
	$.ajax({
		type : "post",
		url : "qnalist",
		data : JSON.stringify({"product_id" : prd_id,"pagination" : { "page" : page, "length" : length}}),
		traditional : true,
		contentType: "application/json; charset=UTF-8",
		success : function(json){
			$("#qnaArea").children().remove();
			$("#pagination").children().remove();

			let qnalist = "";
			if(json["qna"].length !== 0){
				json["qna"].forEach((element, index)=>{
					qnalist += "<tr>";
					qnalist += "<td>" + parseInt(json["qnaAllLength"] - parseInt(((page - 1) * 10) + index)) + "</td>";
					if(element.is_answer == 0){
						qnalist += "<td>답변대기</td>";
					} else if(element.is_answer == 1){
						qnalist += "<td>답변완료</td>";
					}
					qnalist += "<td>";
					qnalist += "<input type='hidden' name='id' value='" + qnaId + "'>";
					qnalist += "<input type='hidden' name='gnum' value='" + qnaGnum + "'>";
					qnalist += "<a id='title' href='#'>";
					if(element.title.length > 15){
						qnalist += element.title.substring(0,15) + "...";
					}else{
						qnalist += element.title;
					}
					qnalist += "</a>";
					qnalist += "</td>";
					
					qnalist += "<td>" + element.user_email.substring(0,2) + "***" + element.user_email.substring(element.user_email.indexOf("@"), element.user_email.length) + "</td>";
					qnalist += "<td>" + element.regdate + "</td>";
					qnalist += "</tr>";
					qnalist += "<tr class='qna-answer text-left'>";
					qnalist += "</tr>";
				});
			}else{
				qnalist += "<tr>";
				qnalist += "<td class='text-center' colspan='5'>등록된 게시글이 존재하지 않습니다.</td>";
				qnalist += "</tr>";
			}
				
			let pagination = "";
			pagination += '<div class="pagination">';
			if( json["pagination"].currentBlock != 1 ){
				pagination += '<a href="javascript:pageLoad(' +parseInt(json["pagination"].startPage) - parseInt(json["pagination"].pageLength) + ')" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>';
			}
			for(let page = json["pagination"].startPage, endPage = json["pagination"].endPage ; page <= endPage ; page++){
				if(page == json["pagination"].page){
					pagination += '<a href="javascript:pageLoad(' + page + ')" class="active">' + page + '</a>';
				}else{
					pagination += '<a href="javascript:pageLoad(' + page + ')">' + page + '</a>';
				}
			}
			
			if( json["pagination"].currentBlock != json["pagination"].totalBlock ){
				pagination += '<a href="productlist?page=' + (parseInt(json["pagination"].endPage) + 1) + '" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>';
			}
			
			$("#qnaArea").html(qnalist);
			$("#pagination").html(pagination);
				
		},
		error : function(){
			alert("pagingAjax error");
		}
	});
}
/* 페이징 처리 구간 End */
