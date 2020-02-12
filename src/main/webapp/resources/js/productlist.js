$(document).ready(function(){
	defaultFilter();
	$("#brand-filter").on("change", checkBrand);
	$("select[name = sorting]").on("change", checkSorting);
	$("select[name = length]").on("change", checkLength);
});

let arrBrand = new Array();
let page = 1, sorting = 1, length = 6;
let lower, upper;

function checkForHash(page){
	document.location.hash = "#" + page;
    if(document.location.hash){
        var HashLocationName = document.location.hash;
        HashLocationName = HashLocationName.replace("#","");
    }
}

function defaultFilter(){
	let brandIds = $("#brand-filter").find("input[name = brand_id]");
	for(let i=0, size=brandIds.size() ; i < size ; i++){
		if($(brandIds[i]).is(":checked")){
			arrBrand.push($(brandIds[i]).val());
			filterAjax(page);
			break;
		}
	}
}

const checkPrice = ()=>{
	lower = $("#lower-value").text().replace(/,/g,"");
	upper = $("#upper-value").text().replace(/,/g,"");
	
	if(lower == ""){
		lower = 0;
	}
	if(upper == ""){
		upper = 200000;
	}
	filterAjax(page);
}

const checkLength = ()=>{
	length = $("select[name = length] option:selected").val();
	filterAjax(page);
}

const checkSorting = (evt)=>{
	sorting = $("select[name = sorting] option:selected").val();
	filterAjax(page);
}

const checkBrand = (evt)=>{
	if($(evt.target).is(":checked")){
		arrBrand.push($(evt.target).val());
		filterAjax(page);
	}else{
		arrBrand.splice(arrBrand.indexOf($(evt.target).val()), 1);
		filterAjax(page);
	}
}

// url의 카테고리를 읽는다.
const getUrlParams = ()=>{
    let params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
    return params;
}

const filterAjax = function(page){
	let oParam = getUrlParams();
	$.ajax({
		type : "post",
		url : "/portfolio/productlist/checkBrand",
		data : JSON.stringify({"lower" : lower, "upper" : upper, "arrBrand" : arrBrand, "categorym_id" : oParam.categorym_id, "categorys_id" : oParam.categorys_id, "sorting" : sorting, "pagination" : { "page" : page, "length" : length}}),
		traditional : true,
		contentType: "application/json; charset=UTF-8",
		success : (json)=>{
				$("#product-list").children().remove();
				$("#pagination").children().remove();
				let prdList = "";
				if(json["product"].length !== 0){
					json["product"].forEach(function(element, index){
						prdList += '<div class="col-lg-4 col-md-6">';
						prdList += '<a href="' + '/portfolio/singleproduct?id=' + element.id + '&page=' + json["pagination"].page + '">';
						prdList += '<div class="single-product">';
						prdList += '<img class="img-fluid" src="/portfolio/resources/img/product/' + element.image_name + '" alt="' + element.image_name + '">';
						prdList += '<div class="product-brand">' + element.brand_name + '</div>';
						prdList += '<div class="product-details">';
						prdList += '<h6>' + element.name + '</h6>';
						prdList += '<div class="price">';
						prdList += '<h6>' + element.price.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '원</h6>';
						prdList += '</div>';
						prdList += '</div>';
						prdList += '</div>';
						prdList += '</a>';
						prdList += '</div>';
					});
				}else{
					prdList += '<div class="inner-wrap text-center float-hidden">';
					prdList += '상품이 존재하지 않습니다.';
					prdList += '</div>';
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
			$("#product-list").html(prdList);
			$("#pagination").html(pagination);
		},
		error : ()=>{
			alert("Filter Ajax 에러");
		}
	});
}

const pageLoad = function(page){
	checkForHash(page);
	filterAjax(page);
}


$(window).on('popstate', function(){
	let url = location.href;
	let currentPage = url.split("#")[1];
	filterAjax(currentPage);
});