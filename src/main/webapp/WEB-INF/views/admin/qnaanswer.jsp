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
	<%@include file="../include/header.jsp"%>
	<!-- End Header Area -->
	<%@include file="include/manager.jsp" %>		

			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40">
					<div class="row">
					<h2 class="title">답변하기</h2>
						<div class="container">
				            <div class="cart_inner">
				                <div class="table-responsive">
     	                  			<form name="qnaForm" action="${pageContext.request.contextPath}/qnamanager/qnaanswer" method="post">
					                    <table id="userModify-table" class="userModify-table">
					                    	<tbody>
						                    	<tr>
					                    			<td class="td_title">문의 제목</td>
					                    			<td class="td_content">
					                    				${qna.title}
					                    			</td>
					                    		</tr>
					                    		<tr>
					                    			<td class="td_title">문의 내용</td>
					                    			<td class="td_content">
					                    				${qna.content}
					                    			</td>
					                    		</tr>
					                    		<tr>
					                    			<td class="td_title">답변 내용</td>
					                    			<td class="td_content">
					                    				<textarea class="single-textarea" name="content"><c:if test="${answer != null}">${answer.content}</c:if></textarea>
					                    			</td>
					                    		</tr>
					                    	</tbody>
					                    </table>
										
			                    		<div class="btn-group">
			                    			<a id="btnSave" class="blue_btn small_btn" href="#">저장</a>
			                    			<a class="blue_btn small_btn" href="${pageContext.request.contextPath}/qnamanager">취소</a>
			                    			<a id="btnDelete" class="blue_btn small_btn" href="#">삭제</a>
			                    		</div>
			                    		<input type="hidden" name="id" value="${qna.id}">
			                    		<input type="hidden" name="product_id" value="${qna.product_id}">
			                    		<input type="hidden" name="gnum" value="${gnum}">
			                    		<input type="hidden" name="is_answer" value="${qna.is_answer}">
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
	<%@include file="../include/footer.jsp" %>
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
	<script src="${pageContext.request.contextPath}/resources/js/qnaanswer.js"></script>
</body>

</html>