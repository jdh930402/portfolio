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
    <title> 주문 페이지 - Karma Shop</title>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loading.css">
</head>

<body>

    <!-- Start Header Area -->
	<%@include file="include/header.jsp"%>
	<!-- End Header Area -->

    <!-- Start Banner Area -->
    <section class="banner-area organic-breadcrumb">
        <div class="container">
            <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                <div class="col-first">
                    <h1>주문 페이지</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- End Banner Area -->

    <!--================Checkout Area =================-->
    <section class="checkout_area section_gap">
        <div id="checkout" class="container">

            <div class="billing_details">
                <div class="row">
                    <div class="col-lg-8">
                        <h3>배송지 정보</h3>
                        <form class="row contact_form" action="#" name="addrForm" method="post" novalidate="novalidate">
                            <div class="col-md-6 form-group p_star">
                            	<strong>배송지 선택</strong>
                   	         	<select class="addrInfo" name="addrInfo">
                            		<option value="0">등록 배송지</option>
                            		<option value="1">신규 배송지</option>
                            	</select>
                            </div>
                            <div class="col-md-6 form-group p_star">
                            	<a href="#" id="btnAddrList" class="genric-btn success radius">배송지 목록</a>
                            </div>
                            
                            <div class="col-md-6 form-group p_star">
                                <input type="text" class="form-control" id="name" name="recipient" placeholder="수령인 *" value="${defaultAddr.recipient}" readonly="readonly">
                                <span id="recipientMessage" class="message"></span>
                            </div>
                            <div class="col-md-6 form-group p_star">
                                <input type="text" class="form-control" id="tel" name="tel" placeholder="핸드폰 번호 *" value="${defaultAddr.tel}" readonly="readonly">
                                <span id="telMessage" class="message"></span>
                            </div>
                            <div class="col-md-6 form-group p_star">
                                <input type="text" class="form-control" id="addr1" name="addr1" placeholder="우편번호 *" value="${defaultAddr.addr1}" readonly="readonly">
                            </div>
                            <div class="col-md-6 form-group p_star">
                            	<a href="#" id="btnSearchAddr" class="genric-btn success radius">주소 검색</a>
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="addr2" name="addr2" placeholder="기본주소 *" value="${defaultAddr.addr2}" readonly="readonly">
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="addr3" name="addr3" placeholder="상세주소 *" value="${defaultAddr.addr3}" readonly="readonly">
                                <span id="addrMessage" class="message"></span>
                            </div>
                            <c:choose>
	                           	<c:when test="${product != null}">
	                            	<input type="hidden" name="product_id" value="${product.id}">
	                            	<input type="hidden" name="productoption_id" value="${prdOption.id}">
	                            	<input type="hidden" name="quantity" value="${product.quantity}">
	                            	<input type="hidden" name="price" value="${product.quantity * product.price}">
	                            	<input type="hidden" name="cartlist" value="n">
	                            </c:when>
	                            
	                            <c:when test="${cartlist != null}">
	                            	<input type="hidden" name="cartlist" value="y">
	                            </c:when>
                            </c:choose>
                            
                        </form>
                    </div>
                    <div class="col-lg-4">
                        <div class="order_box">
                            <h2>주문 내역</h2>
                            <ul class="list">
                            <c:choose>
                            	<c:when test="${cartlist != null}">
		                            <c:forEach var="cartlist" items="${cartlist}">
		                                <li><a href="#">${cartlist.name}(${cartlist.size})<span class="middle">x ${cartlist.quantity}</span> <span class="last"><fmt:formatNumber value="${cartlist.quantity * cartlist.price}" pattern="#,###원"/></span></a></li>
		                                <c:set var="prdTotal" value="${prdTotal + cartlist.quantity * cartlist.price}"/>
		                            </c:forEach>
                           	 	</c:when>
                            	<c:when test="${product != null}">
		                        	<li><a href="#">${product.name}(${prdOption.size}) <span class="middle">x ${product.quantity}</span> <span class="last"><fmt:formatNumber value="${product.quantity * product.price}" pattern="#,###원"/></span></a></li>
	                                <c:set var="prdTotal" value="${product.quantity * product.price}"/>
                           	 	</c:when>
                            </c:choose>
                            </ul>
                            
                            <ul class="list list_2">
                                <li><a href="#">상품 금액 <span><fmt:formatNumber value="${prdTotal}" pattern="#,###원"/></span></a></li>
                                <li><a href="#">배송비(3만원 이상 무료) 
                                <span>
                                	<c:choose>
                                		<c:when test="${prdTotal > 30000 }">
                                			무료
                                		</c:when>
                                		<c:otherwise>
                                			<c:set var="shipping" value="5000"/>
                                			<fmt:formatNumber value="${shipping}" pattern="#,###원"/>
                                		</c:otherwise>
                                	</c:choose>
                                </span></a></li>
                                <li><a href="#">전체 금액 <span><fmt:formatNumber value="${prdTotal + shipping}" pattern="#,###원"/></span></a></li>
                            </ul>
                            <div class="creat_account">
                                <input type="checkbox" id="f-option4" name="selector">
                                <label for="f-option4">위 주문 내용을 확인 하였으며, 회원 본인은 결제에 동의합니다.</label>
                            </div>
                            <span id="checkMessage" class="message"></span>
                            <a id="btnCheckout" class="primary-btn" href="#">결제하기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="loading_area" class="invisible">
			<h1 id="loadingMessage" class="loading">결제가 진행중입니다.</h1>
			<div id="loading"></div>
		</div>
    </section>
    <!--================End Checkout Area =================-->
	
    <!-- start footer Area -->
    <%@include file="include/footer.jsp" %>
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
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/checkout.js"></script>
</body>

</html>