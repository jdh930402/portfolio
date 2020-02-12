<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="resources/img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>회원가입 - My Shop</title>

	<!--
		CSS
		============================================= -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/linearicons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/themify-icons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nice-select.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nouislider.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/register.css">
</head>

<body>

<%@include file="include/header.jsp"%>
	<!-- End Header Area -->

	<!-- Start Banner Area -->
	<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>로그인/회원가입</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->

	<!--================Login Box Area =================-->
	<section>
		<div class="container">
			<div class="col-lg-12">
				<div class="login_form_inner">
					<h3>회원가입</h3>
					<form id="contactForm" class="row login_form" name="regForm" action="register" method="post" novalidate="novalidate">
						<div class="col-md-12 form-group">
							<input type="email" class="form-control" name="email" placeholder="이메일">
							<p id="emailMessage" class="message"></p>
						</div>
						<div class="col-md-12 form-group">
							<input type="password" class="form-control" name="password" placeholder="비밀번호">
							<p id="passwordMessage" class="message"></p>
						</div>
						<div class="col-md-12 form-group">
							<input type="password" class="form-control" name="rePassword" placeholder="비밀번호 확인">
							<p id="rePasswordMessage" class="message"></p>
						</div>
						<div class="col-md-12 form-group">
							<input type="text" class="form-control" name="name" placeholder="이름">
							<p id="nameMessage" class="message"></p>
						</div>
						<div class="col-md-12 form-group">
							<input type="text" class="form-control" name="tel" placeholder="핸드폰번호">
							<p id="telMessage" class="message"></p>
						</div>
						<div class="col-md-12 form-group">
							<button id="btnRegister" type="button" class="primary-btn">회원가입</button>
							<p>만 14세 이상이며, 이용약관, 전자금융거래이용약관, 개인정보 수집 및 이용, 개인정보 제공 내용을 확인 및 동의합니다.</p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!--================End Login Box Area =================-->

	<!-- start footer Area -->
	<%@include file="include/footer.jsp"%>
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
	<script src="${pageContext.request.contextPath}/resources/js/gmaps.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<!-- customer Js -->
	<script src="${pageContext.request.contextPath}/resources/js/register.js"></script>
</body>

</html>