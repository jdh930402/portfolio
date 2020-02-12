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
	<title>배송지 추가 - My Shop</title>

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
	<%@include file="include/header.jsp"%>
	<!-- End Header Area -->
	<%@include file="include/mypage.jsp" %>		

			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40">
					<div class="row">
					<h2 class="title">배송지 수정</h2>
						<div class="container">
				            <div class="cart_inner">
				                <div class="table-responsive">
     	                  			<form name="addrForm" action="${pageContext.request.contextPath}/addressManage/modify" method="post">
					                    <table id="userModify-table" class="userModify-table">
					                    	<tbody>
					                    		<tr>
					                    			<td class="td_title">*수령인</td>
					                    			<td class="td_content"><input class="large-input" type="text" name="recipient" value="${address.recipient}">
					                    				<span id="recipientMessage" class="message"></span>
					                    			</td>
					                    		</tr>
					                    		<tr>
					                    			<td class="td_title">*주소</td>
					                    			<td class="td_content">
						                    			<table class="inner-table">
						                    				<tr>
							                    				<td><input class="addr1" type="text" name="addr1" placeholder="우편 번호" readonly="readonly" value="${address.addr1}">
									                    			<div class="modify-btn">
							                    						<a id="btnSearch" class="gray_btn small_btn" href="index">검색</a>
							                    					</div>
							                    				</td>
						                    				</tr>
						                    				<tr>
							                    				<td><input class="large-input" type="text" name="addr2" placeholder="기본 주소" readonly="readonly" value="${address.addr2}"></td>
						                    				</tr>
						                    				<tr>
							                    				<td><input class="large-input" type="text" name="addr3" placeholder="상세 주소" value="${address.addr3}"></td>
						                    				</tr>
						                    				<tr>
						                    					<td><span id="addrMessage" class="message"></span></td>
						                    				</tr>
						                    			</table>
					                    			</td>
					                    		</tr>
					                    		<tr>
					                    			<td class="td_title">*핸드폰번호</td>
					                    			<td class="td_content"><input class="large-input" type="text" name="tel" value="${address.tel}"> 
					                    				<span id="telMessage" class="message"></span>
					                    			</td>
					                    		</tr>
					                    	</tbody>
					                    </table>
					                    <div class="checkbox-group">
					                    	<input id="checkbox"type="checkbox" name="depth" value="0" <c:if test="${address.depth == 0}">checked = "checked"</c:if> ><label class="label" for="checkbox">기본배송지로 설정</label>
					                    </div>
			                    		<div class="btn-group">
			                    			<input type="hidden" name="id" value="${address.id}"> 
			                    			<a id="btnSave" class="blue_btn small_btn" href="${pageContext.request.contextPath}/addressManage#">저장</a>
			                    			<a class="blue_btn small_btn" href="${pageContext.request.contextPath}/addressManage">취소</a>
			                    		</div>
				                    </form>
			                	</div>
			                </div>
		                </div>
					
					</div>
				</section>
				<!-- End Best Seller -->
			</div>
		</div>
	</div>
	<!-- start footer Area -->
	<%@include file="include/footer.jsp" %>
	<!-- End footer Area -->

	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/vendor/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.ajaxchimp.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.sticky.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/nouislider.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/gmaps.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/address.js"></script>
</body>

</html>