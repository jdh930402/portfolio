<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>마이페이지</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->
	
	<div class="container">
		<div class="row bottom">
			<div class="col-xl-3 col-lg-4 col-md-5">
				<div class="sidebar-categories">
					<div class="head">마이페이지 메뉴</div>
					<ul class="main-categories">
						<li class="main-nav-list"><h4>My 쇼핑</h4>
						
						<c:set var="servletPath" value="${ requestScope['javax.servlet.forward.servlet_path']}"/>
								<li class="main-nav-list child <c:if test="${servletPath eq '/purchase/list'}">active</c:if>"><a href="${pageContext.request.contextPath}/purchase/list">주문목록/배송조회</a></li>
								<li class="main-nav-list child <c:if test="${servletPath eq '/cancelreturn/list'}">active</c:if>"><a href="${pageContext.request.contextPath}/cancelreturn/list">취소/반품내역</a></li>
						</li>

						<li class="main-nav-list"><h4>My 정보</h4>
								<li class="main-nav-list child <c:if test="${servletPath eq '/login/userModify'}">active</c:if>"><a href="${pageContext.request.contextPath}/login/userModify">개인정보확인/수정</a></li>
								<li class="main-nav-list child <c:if test="${servletPath eq '/addressManage'}">active</c:if>"><a href="${pageContext.request.contextPath}/addressManage" class="border-bottom-0">배송지 관리</a></li>
						</li>
					</ul>
				</div>
			</div>	
