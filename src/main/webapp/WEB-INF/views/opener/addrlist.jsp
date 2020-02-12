<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<title>배송지 관리 - My Shop</title>

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
<body>
	<div class="col-xl-9 col-lg-8 col-md-7">
			<!-- Start Best Seller -->
			<section class="lattest-product-area pb-40">
				<div class="row">
				<h2 style="width: 100%;" class="title text-center">배송지 관리</h2>
					<div class="container">
			            <div class="cart_inner">
			                <div class="table-responsive">
			                    <table class="address-table">
			                    	<colgroup>
			                    		<col width="25%">
			                    		<col width="45%">
			                    		<col width="30%">
			                    	</colgroup>
			                    	<thead>
			                        	<tr>
				                            <th>수령인</th>
				                            <th>주소</th>
				                            <th>연락처</th>
				                        </tr>
			                    	</thead>
			                        <tbody>
				                        <c:set var="address" value="${address}"/>
				                        <c:choose>
				                        <c:when test="${ fn:length(address) != 0 }">
							            	<c:forEach var="address" items="${address}">
					                        <tr class="tr_content active">
					                        	<td>${address.recipient}</span><br><c:if test="${address.depth == 0}"><strong>기본배송지</strong></c:if></td>
					                        	<td class="text-left"><div>${address.addr1}</div><div>${address.addr2}</div><div>${address.addr3}</div></td>
					                        	<td>${address.tel}</td>
					                        </tr>
							                </c:forEach>
						               	</c:when>
						               	<c:otherwise>
						                	<tr>
						                		<td colspan="4">등록된 주소지가 없습니다. </td>
						                	<tr>
						                </c:otherwise>
						                </c:choose>
			                        
			                        </tbody>
			                    </table>
			                </div>
			            </div>
			        </div>
				</div>
			</section>
			<!-- End Best Seller -->
		</div>
			
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
	<script src="${pageContext.request.contextPath}/resources/js/checkout.js"></script>
</body>
</html>