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
    <title>장바구니 - Karma Shop</title>

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

<body>

    <!-- Start Header Area -->
	<%@include file="include/header.jsp"%>
	<!-- End Header Area -->

    <!-- Start Banner Area -->
    <section class="banner-area organic-breadcrumb">
        <div class="container">
            <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                <div class="col-first">
                    <h1>장바구니</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- End Banner Area -->

    <!--================Cart Area =================-->
    <section class="cart_area">
        <div class="container">
            <div class="cart_inner">
                <div class="table-responsive">
                    <table class="table">
                    	<colgroup>
                    		<col width="40%">
                    		<col width="15%">
                    		<col width="20%">
                    		<col width="15%">
                    		<col width="10%">
                    	</colgroup>
                        <thead>
                            <tr>
                                <th scope="col">상품</th>
                                <th scope="col">가격</th>
                                <th scope="col">수량</th>
                                <th scope="col">합계</th>
                                <th scope="col">제거</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:set var="cartLength" value="${fn:length(cartlist)}"/>
                        <c:choose>
	                        <c:when test="${cartLength == 0}">
	                        	<tr>
	                        		<td colspan="4" class="text-center">장바구니가 비어있습니다.</td>
	                        	<tr>
	                        
	                        </c:when>
	                        
	                        <c:otherwise>
		                        	<c:forEach var="cartlist" items="${cartlist}">
		                            <tr>
		                                <td class = "firstTd">
		                                	<a href="singleproduct?id=${cartlist.product_id}">
		                                    <div class="media">
		                                        <div class="d-flex">
		                                            <img class="cartlist-img" src="${pageContext.request.contextPath}/resources/img/product/${cartlist.image_name}" alt="${cartlist.name}">
		                                        </div>
		                                        <div class="media-body">
		                                            <p>${cartlist.name}(${cartlist.size})</p>
		                                        </div>
		                                    </div>
		                                    </a>
		                                </td>
		                                <td>
		                                    <h5><fmt:formatNumber value="${cartlist.price}" pattern="#,###원"/></h5>
		                                </td>
		                                <td>
		                                    <div class="product_count">
		                                    	<input type="hidden" value="${cartlist.id}">
		                                        <input type="number" name="quantity" maxlength="12" value="${cartlist.quantity}" title="Quantity:"
		                                            class="input-text qty">
		                                        <button id="btnUp" class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i></button>
		                                        <button id="btnDown" class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i></button>
		                                    </div>
		                                </td>
		                                <td>
		                                    <h5><span id="price"><fmt:formatNumber value="${cartlist.quantity * cartlist.price}" pattern="#,###원"/></span></h5>
		                                    <c:set var="totalVal" value="${totalVal + cartlist.quantity * cartlist.price}"/>
		                                </td>
		                                <td>
		                                	<a href="" class="genric-btn default-border">X</a>
		                                </td>
		                            </tr>
		                            </c:forEach>
	                            </c:otherwise>
                       		</c:choose>
                            <tr>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>결제금액</h5>
                                </td>
                                <td>
                    				<c:choose>
                    					<c:when test="${cartLength == 0}">
		                                    <h5>0원</h5>
                    					</c:when>
                    					<c:otherwise>
		                                    <h5><span id="totalPrice"><fmt:formatNumber value="${totalVal}" pattern="#,###원"/></span></h5>
                    					</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr class="out_button_area">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="checkout_btn_inner d-flex align-items-center">
                                        <a id="btnShopping" class="gray_btn" href="productlist">쇼핑하기</a>
                                        <a id="btnCheckout" class="primary-btn" href="checkout">결제하기</a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
    <!--================End Cart Area =================-->

    <!-- start footer Area -->
    <footer class="footer-area section_gap">
    	<%@include file="include/footer.jsp" %>
    </footer>
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
	<script src="${pageContext.request.contextPath}/resources/js/cartlist.js"></script>
</body>

</html>