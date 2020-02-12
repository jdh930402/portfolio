<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>상품리스트 - Karma Shop</title>

	<!--
            CSS
            ============================================= -->
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

	<!-- Start Banner Area -->
	<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>상품리스트</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->
	<div class="container">
		<div class="row">
			<div class="col-xl-3 col-lg-4 col-md-5">
				<div class="sidebar-categories">
					<div class="head">카테고리</div>
					<ul class="main-categories">
						<li class="main-nav-list <c:if test="${param.categorys_id == null}">active</c:if>"><a href="${pageContext.request.contextPath}/productlist">전체상품</a></li>
						<c:forEach var="categorym" items="${categorym}">
						<li class="main-nav-list"><a class="categorym" data-toggle="collapse" href="#${categorym.name}" aria-expanded="false" aria-controls="meatFish"><span
								 class="lnr lnr-arrow-right"></span>${categorym.name}</a>
							<ul class="collapse <c:if test='${param.categorym_id == categorym.id}'>show</c:if>" id="${categorym.name}" data-toggle="collapse" aria-expanded="false" aria-controls="meatFish">
								<c:forEach var="categorys" items="${categorys}">
								<c:choose>
									<c:when test="${categorym.id == categorys.categorym_num }">
										<li class="main-nav-list child <c:if test="${param.categorys_id == categorys.id}">active</c:if>"><a href="${pageContext.request.contextPath}/productlist?categorym_id=${categorym.id}&categorys_id=${categorys.id}">${categorys.name}</a></li>
									</c:when>
								</c:choose>
								</c:forEach>
							</ul>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="sidebar-filter mt-50">
					<div class="top-filter-head">Product Filters</div>
					<div class="common-filter">
						<div class="head">브랜드</div>
						<ul id="brand-filter">
							<c:forEach var="brand" items="${brand}">
							<li class="filter-list"><input class="pixel-radio" type="checkbox" id="${brand.name}" name="brand_id" value="${brand.id}"<c:if test="${param.brand_id == brand.id}">checked="checked"</c:if>> <label for="${brand.name}">${brand.name}</label></li>
							</c:forEach>
						</ul>
					</div>
					<div class="common-filter">
						<div class="head">가격</div>
						<div class="price-range-area">
							<div id="price-range"></div>
							<div class="value-wrapper d-flex">
								<div class="price">가격:</div>
								<div id="lower-value"></div>
								<span>원</span>
								<div class="to">~</div>
								<div id="upper-value"></div>
								<span>원</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xl-9 col-lg-8 col-md-7">
				<div class="filter-bar d-flex flex-wrap align-items-center">
					<div class="sorting">
						<select name="sorting">
							<option value="1" selected="selected">인기순</option>
							<option value="2">낮은 가격순</option>
							<option value="3">높은 가격순</option>
							<option value="4">최신순</option>
						</select>
					</div>
					<div class="sorting mr-auto">
						<select name="length">
							<option value="6" selected="selected">6개 묶어보기</option>
							<option value="12">12개 묶어보기</option>
							<option value="18">18개 묶어보기</option>
						</select>
					</div>
				</div>
			
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40 category-list">
					<div id="product-list" class="row">
						<c:set var="productLength" value="${fn:length(product)}"/>
						<c:choose>
						<c:when test="${productLength == 0}">
							<div class="inner-wrap text-center float-hidden">
							상품이 존재하지 않습니다.				
							</div>
						</c:when>
						
						<c:otherwise>
							<c:forEach var="product" items="${product}">
							<!-- single product start -->
							<div class="col-lg-4 col-md-6">
								<a href="${pageContext.request.contextPath}/singleproduct?id=${product.id}&page=${pagination.page}">
									<div class="single-product">
									<img class="img-fluid" src="${pageContext.request.contextPath}/resources/img/product/${product.image_name}" alt="${product.image_name}">
									<div class="product-brand">${product.brand_name}</div>
									<div class="product-details">
										<h6>${product.name}</h6>
										<div class="price">
											<h6><fmt:formatNumber value="${product.price}" pattern="#,###원"/></h6>
										</div>
									</div>
								</div>
								</a>
							</div>
							<!-- single product end -->
							</c:forEach>
						</c:otherwise>
						</c:choose>
					</div>
				</section>
				<!-- End Best Seller -->
				<!-- Start Filter Bar -->
				<div id="pagination" class="filter-bar d-flex flex-wrap align-items-center">
					<div class="pagination">
						<c:if test="${pagination.currentBlock != 1 }">
							<a href="javascript:pageLoad(${pagination.startPage - pagination.pageLength})" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
						</c:if>
						
						<c:forEach var="page" begin="${pagination.startPage}" end="${pagination.endPage}">
							<a href="javascript:pageLoad(${page})" <c:if test='${pagination.page == page}'>class="active"</c:if>>${page}</a>
						</c:forEach>
						
						<c:if test="${pagination.currentBlock != pagination.totalBlock}">
							<a href="javascript:pageLoad(${pagination.endPage+1})" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
						</c:if>
					</div>
				</div>
				<!-- End Filter Bar -->
			</div>
		</div>
	</div>

	<!-- Start related-product Area -->
	<section class="related-product-area section_gap">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6 text-center">
					<div class="section-title">
						<h1>Deals of the Week</h1>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore
							magna aliqua.</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-9">
					<div class="row">
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r1.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r2.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r3.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r5.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r6.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r7.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r9.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r10.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r11.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="ctg-right">
						<a href="#" target="_blank">
							<img class="img-fluid d-block mx-auto" src="img/category/c5.jpg" alt="">
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End related-product Area -->

	<!-- start footer Area -->
	<%@include file="include/footer.jsp"%>
	<!-- End footer Area -->


	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
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
	<script src="${pageContext.request.contextPath}/resources/js/productlist.js"></script>
</body>

</html>