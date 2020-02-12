<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>상품등록관리 - My Shop</title>

	<!--
            CSS
            ========================================================= -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/linearicons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/themify-icons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nice-select.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nouislider.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>

<body id="category">

	<!-- Start Header Area -->
	<%@include file="../include/header.jsp"%>
	<!-- End Header Area -->
	<%@include file="include/manager.jsp" %>		

			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40">
					<div class="row">
					<!-- check password area Start -->
					<!-- check password area End -->
					<!-- print UserData area Start -->
					<h2 class="title">상품 등록</h2>
						<div class="container">
				            <div class="cart_inner">

				           		<form name="regForm" action="/portfolio/product/register" method="post" enctype="multipart/form-data">
				                    <table id="product-table"class="product-table">
				                    	<tbody>
				                    		<tr>
				                    			<td class="td_title">*카테고리 선택</td>
				                    			<td colspan="3">
					                    			<select name="categorym_id">
					                    			<option value="">선택</option>
					                    			<c:forEach var="categorym" items="${categorym}">
					                    			<option value="${categorym.id}">${categorym.name}</option>
					                    			</c:forEach>
					                    			</select>
					                    			<span class="select-span">></span>
					                    			<select name="categorys_id">
					                    				<option value="">선택</option>
					                    			</select>
					                    			<div>
					                    				<span id="categoryMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">*브랜드</td>
				                    			<td colspan="2">
					                    			<select name="brand_id">
					                    				<option value="">선택</option>
					                    				<c:forEach var="brand" items="${brand}">
					                    					<option value="${brand.id}">${brand.name}</option>
					                    				</c:forEach>
					                    			</select>
					                    			<div>
					                    				<span id="brandMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">*상품명</td>
				                    			<td colspan="2"><input class="large-input" type="text" name="name">
				                    				<div>
					                    				<span id="nameMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">*가격</td>
				                    			<td><input class="small-input text-center" type="number" name="price"><span>원</span>
				                    				<div>
					                    				<span id="priceMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">*원산지</td>
				                    			<td colspan="2">
					                    			<select name="origin_id">
					                    				<option value="">선택</option>
					                    				<c:forEach var="origin" items="${origin}">
					                    					<option value="${origin.id}">${origin.country}</option>
					                    				</c:forEach>
					                    			</select>
					                    			<div>
					                    				<span id="originMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">*메인 이미지</td>
				                    			<td colspan="2"> 	
					                    			<div class="custom-file">
					                    				<input type="file" class="custom-file-input" id="mainImage" name="main" aria-describedby="inputGroupFileAddon04">
	    												<label id="fileLabel" class="custom-file-label" for="mainImage">Choose file</label>
	 												</div>
	 												<div>
					                    				<span id="mainImgMessage" class="message"></span>
					                    			</div>
 												</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">상품 이미지</td>
				                    			<td colspan="2"> 	
					                    			<div class="custom-file">
					                    				<input type="file" class="custom-file-input" id="optionImage" name="option" aria-describedby="inputGroupFileAddon04" multiple="multiple">
	    												<label id="fileLabel" class="custom-file-label" for="optionImage">Choose file</label>
	 												</div>
 												</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">상세 이미지</td>
				                    			<td colspan="2">
					                    			<div class="custom-file">
					                    				<input type="file" class="custom-file-input" id="detailImage" name="detail" aria-describedby="inputGroupFileAddon04" multiple="multiple">
	    												<label id="fileLabel" class="custom-file-label" for="detailImage">Choose file</label>
	 												</div>
 												</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">*사이즈/재고량</td>
				                    			<td>
				                    				<select id="size"  name="size_id">
				                    					<option value="0">선택</option>
				                    					<c:forEach var="size" items="${size}">
				                    						<option value="${size.id}">${size.size}</option>
				                    					</c:forEach>
				                    				</select>
				                    				<span>/</span>
				                    				<input id="amount" class="small-input text-center" type="number" name="amount"><span>개</span>
				                    				<div>
					                    				<span id="sizeAmountMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    			<td>
				                    				<button id="btnAddSize" type="button" class="btn btn-outline-primary btn-sm btn-option float-right">추가</button>
				                    			</td>
				                    		</tr>
				                    	</tbody>
				                    </table>
				                    <div class="bottom"><span class="message">※ 메인 이미지를 제외한 이미지는 복수개 선택 가능하며 등록시 순서대로 등록하여주세요.</span></div>
				                    <table class="product-table bottom">
				                    	<tbody id="addSize">
				                    	</tbody>
				                    </table>
			                    	<div class="btn-group">
		                    			<a id="btnRegister" class="blue_btn small_btn" href="index">등록</a>
		                    			<a id="btnCancel" class="blue_btn small_btn" href="index">취소</a>
		                    		</div>
		                    	</form>
			                </div>
		                </div>
					</div>
				</section>
				<!-- End Best Seller -->
			</div>
		</div>
	</div>
	<!-- start footer Area -->
	<%@include file="../include/footer.jsp" %>
	<!-- End footer Area -->

	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery.form.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/vendor/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.ajaxchimp.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.sticky.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/nouislider.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="${pageContext.request.contextPath}/resources/js/gmaps.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/prdregister.js"></script>
</body>

</html>