<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<header>
	<div class="util-wrap">	
		<ul class="util">
			<c:if test="${email == null && id == null}">
				<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/register">회원가입</a></li>
			</c:if>
			<c:if test="${email != null}">
				<li><a href="${pageContext.request.contextPath}/purchase/list">${name}님</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
			</c:if>
			
			<c:if test="${id != null}">
				<li><a href="#">관리자</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
			
			</c:if>
			<c:if test="${id == null}">
				<li><a href="${pageContext.request.contextPath}/cartlist">장바구니</a></li>
				<li><a href="">주문배송</a></li>
				<li><a href="">고객센터</a></li>
			</c:if>
		</ul>			
	</div>
<!-- Start Header Area -->
<div class="header_area sticky-header">
	<div class="main_menu">
		<nav class="navbar navbar-expand-lg navbar-light main_box">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<a class="navbar-brand logo_h" href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/resources/img/logo.png" alt=""></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
				 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto ">
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							 aria-expanded="false">브랜드</a>
							<ul class="brand-menu dropdown-menu scrollable-menu">
								<c:forEach var="brand" items="${brand}">
									<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/productlist?brand_id=${brand.id}"><img alt="${brand.name}" src="${pageContext.request.contextPath}/resources/img/brand/${brand.image}"></a></li>
								</c:forEach>
							</ul>
						</li>
						
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/productlist">전체상품</a></li>
						<!-- categorym 반복&조건 문 -->
						<c:forEach var="categorym"  items="${categorym}">
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							 aria-expanded="false">${categorym.name}</a>
							<ul class="dropdown-menu scrollable-menu">
								<!-- categorys 반복&조건 문 -->
								<c:forEach var="categorys" items="${categorys}">
								<c:choose>
									<c:when test="${categorym.id == categorys.categorym_num }">
										<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/productlist?categorym_id=${categorym.id}&categorys_id=${categorys.id}">${categorys.name}</a></li>
									</c:when>
								</c:choose>
								</c:forEach>
							</ul>
						</li>
						</c:forEach>
						
						<c:if test="${id != null}">
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
									 aria-expanded="false">회원관리</a>
									<ul class="dropdown-menu scrollable-menu">
										<!-- categorys 반복&조건 문 -->
										<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/userInfoManager">회원정보 조회</a></li>
										<li class="nav-item"><a class="nav-link" href="blog.html">구매액 상위 회원 조회</a></li>
									</ul>
								</li>
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
									 aria-expanded="false">상품관리</a>
									<ul class="dropdown-menu scrollable-menu">
										<!-- categorys 반복&조건 문 -->
										<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/product/register">등록 관리</a></li>
										<li class="nav-item"><a class="nav-link" href="blog.html">재고 관리</a></li>
										<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/qnamanager">Q&A 관리</a></li>
									</ul>
								</li>
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
									 aria-expanded="false">주문관리</a>
									<ul class="dropdown-menu scrollable-menu">
										<!-- categorys 반복&조건 문 -->
												<li class="nav-item"><a class="nav-link" href="blog.html">교환 관리</a></li>
												<li class="nav-item"><a class="nav-link" href="blog.html">환불 관리</a></li>
												<li class="nav-item"><a class="nav-link" href="blog.html">배송 관리</a></li>
									</ul>
								</li>
							</c:if>
						
						<li class="nav-item submenu dropdown login-toggle">
							<!-- 로그인을 안한 경우 -->
							<c:if test="${email == null}">
								<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">로그인</a>
							</c:if>
							<!-- 로그인한 경우 -->
							<c:if test="${email != null}">
								<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${name}님</a>
								<ul class="dropdown-menu scrollable-menu">
									<li class="nav-item"><a class="nav-link" href="login.html">마이페이지</a></li>
									<li class="nav-item"><a class="nav-link" href="tracking.html">장바구니</a></li>
									<li class="nav-item"><a class="nav-link" href="elements.html">주문배송</a></li>
									<li class="nav-item"><a class="nav-link" href="elements.html">고객센터</a></li>
									<li class="nav-item"><a class="nav-link" href="elements.html">로그아웃</a></li>
								</ul>
							</c:if>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="nav-item"><a href="cartlist" class="cart"><span class="ti-bag"></span></a></li>
						<li class="nav-item">
							<button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="search_input" id="search_input_box">
		<div class="container">
			<form class="d-flex justify-content-between">
				<input type="text" class="form-control" id="search_input" placeholder="Search Here">
				<button type="submit" class="btn"></button>
				<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
			</form>
		</div>
	</div>
</div>
</header>
